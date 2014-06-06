package com.aravind.ruthvik.analytics.client;

import java.io.File;
import java.util.Collection;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.Logger;

import com.aravind.ruthvik.analytics.collector.AnalyticsDataCollector;
import com.aravind.ruthvik.analytics.collector.SavvisAnalyticsDataCollectorFactory;
import com.aravind.ruthvik.analytics.configuration.AnalyticsConfiguration;
import com.aravind.ruthvik.analytics.configuration.GAQuery;
import com.aravind.ruthvik.analytics.configuration.SavvisAnalyticsConfiguration;
import com.aravind.ruthvik.analytics.data.AnalyticsProvider;
import com.aravind.ruthvik.analytics.data.CommandStatus;
import com.aravind.ruthvik.analytics.data.Dispatcher;
import com.aravind.ruthvik.analytics.dispatcher.AnalyticsDataDispatcher;
import com.aravind.ruthvik.analytics.dispatcher.SavvisAnalyticsDataFileDispatcherFactory;
import com.aravind.ruthvik.analytics.exception.AnalyticsException;
import com.aravind.ruthvik.analytics.exception.AnalyticsTechnicalException;
import com.aravind.ruthvik.analytics.util.AnalyticsUtil;
import com.aravind.ruthvik.analytics.util.CommandLineOptionBuilder;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

/**
 * SavvisDirect GA Data Collector and Dispatcher Job.
 * 
 * @author aravind.ruthvik
 * @since Aug 19, 2013 2:02:03 PM $Id:$
 * 
 */
public class SavvisDirectAnalyticsDataCollectorJob {

	private static final Logger LOG = Logger
			.getLogger(SavvisDirectAnalyticsDataCollectorJob.class);

	private static SavvisAnalyticsConfiguration config = null;
	private static final Boolean HAS_ARG = Boolean.TRUE;
	private static final Boolean REQURED = Boolean.TRUE;
	private static XStream XSTREAM;

	/**
	 * 
	 * @return
	 */
	private static Options buildOptions() {
		LOG.debug("Building command-line options...");
		Options options = new Options();
		options.addOption(CommandLineOptionBuilder.build("c", HAS_ARG, null,
				"Configuration File", REQURED));
		options.addOption(CommandLineOptionBuilder.build("queryId", HAS_ARG,
				null, "GA Query Id which needs to be executed", !REQURED));
		return options;
	}

	/**
	 * 
	 * @param args
	 * @return
	 */
	private static void initialize(String[] args)
			throws AnalyticsTechnicalException {
		LOG.debug("Initializing the job...");
		Options options = buildOptions();
		registerXStreamClasses();
		CommandLine line = null;
		try {
			line = CommandLineOptionBuilder.parseCommandLine(args, options);
			String configFilePath = line.getOptionValue("c");
			LOG.info("Starting to collect the data using configuration file "
					+ configFilePath);
			config = (SavvisAnalyticsConfiguration) XSTREAM.fromXML(new File(
					configFilePath));
			LOG.debug(XSTREAM.toXML(config));

		} catch (ParseException e) {
			LOG.error("Error while initializing the collector job. Message = "
					+ e.getMessage() + " Cause=" + e);
			throw new AnalyticsTechnicalException(e.getMessage(), e);
		}
	}

	private static void registerXStreamClasses() {
		LOG.debug("Registering the XStream classes ...");
		XSTREAM = new XStream(new StaxDriver());
		XSTREAM.processAnnotations(SavvisAnalyticsConfiguration.class);
	}

	/**
	 * Invoking method
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		LOG.info("SavvisDirectAnalyticsDataCollectorJob started");
		try {
			initialize(args);
			if (config != null) {
				for (AnalyticsConfiguration analyticsConfiguration : config
						.getAnalyticsConfigurations()) {
					execute(analyticsConfiguration);
				}
			} else {
				throw new AnalyticsException("Configuration cannot be null");
			}
		} catch (AnalyticsException e) {
			LOG.error("Error while executing the collector job. Reason = "
					+ e.getMessage());
			System.exit(CommandStatus.SEVERE.getErrorCode());
		} catch (AnalyticsTechnicalException e) {
			LOG.error("Error while executing the collector job. Reason = "
					+ e.getMessage());
			System.exit(CommandStatus.WARNING.getErrorCode());
		} catch (Exception e) {
			LOG.error("Error while executing the collector job. Reason = "
					+ e.getMessage());
			System.exit(CommandStatus.FATAL.getErrorCode());
		}
		LOG.info("SavvisDirectAnalyticsDataCollectorJob is completed");
	}

	/**
	 * Loops thru each query and executes them thru appropriate collectors and
	 * dispatchers.
	 * 
	 * @param analyticsConfiguration
	 */
	private static void execute(AnalyticsConfiguration analyticsConfiguration) {
		LOG.info("Processing AnalyticsConfiguration = "
				+ analyticsConfiguration.getName());
		for (GAQuery query : analyticsConfiguration.getGaConfiguration()
				.getGaQueries()) {
			LOG.info("Executing query " + query.getName());
			Collection<?> results;
			try {
				AnalyticsDataCollector<?> collector = SavvisAnalyticsDataCollectorFactory.getCollector(AnalyticsProvider.GOOGLE,
								analyticsConfiguration);
				results = collector.doCollect(query);
				if (results != null && !results.isEmpty()) {
					AnalyticsDataDispatcher<?> dispatcher = SavvisAnalyticsDataFileDispatcherFactory
							.getDispatcher(Dispatcher.GOOGLE_FILE_CSV,AnalyticsUtil.trim(query.getGaQueryOutputFilepath()),results);
					dispatcher.doDispatch();
				} else {
					LOG.warn("No data to dispatch for query " + query.getName());
				}
			} catch (AnalyticsTechnicalException e) {
				LOG.error("Error while executing query " + query.getName()
						+ " Reason=" + e.getMessage() + "Cause=" + e.getCause());
			} catch (AnalyticsException e) {
				LOG.error("Error while executing query " + query.getName()
						+ " Reason=" + e.getMessage() + "Cause=" + e.getCause());
			}
		}
	}
}
