package com.aravind.ruthvik.analytics.collector;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.GeneralSecurityException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.aravind.ruthvik.analytics.configuration.AnalyticsConfiguration;
import com.aravind.ruthvik.analytics.configuration.ControlConfig;
import com.aravind.ruthvik.analytics.configuration.GAConfiguration;
import com.aravind.ruthvik.analytics.configuration.GACredential;
import com.aravind.ruthvik.analytics.configuration.GAQuery;
import com.aravind.ruthvik.analytics.data.AnalyticsQuery;
import com.aravind.ruthvik.analytics.exception.AnalyticsException;
import com.aravind.ruthvik.analytics.exception.AnalyticsTechnicalException;
import com.aravind.ruthvik.analytics.util.AnalyticsUtil;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.GoogleUtils;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.analytics.Analytics;
import com.google.api.services.analytics.Analytics.Data.Ga.Get;
import com.google.api.services.analytics.model.GaData;
import com.google.api.services.analytics.model.GaData.ColumnHeaders;

import static com.aravind.ruthvik.analytics.util.AnalyticsUtil.trim;

/**
 * The implementation class which communicates with Google Analytics Engine.
 * 
 * @author aravind.ruthvik
 * @since Aug 19, 2013 4:38:46 PM $Id:$
 * 
 */
public class GoogleAnalyticsDataCollector<T> extends SavvisAnalyticsDataCollector<T> {

	private static final Logger			LOG						= Logger.getLogger(GoogleAnalyticsDataCollector.class);
	private static final String			CURRENT_DATE_LITERAL	= "CURRENT_DATE";
	private static final String			DATE_FORMAT				= "yyyy-MM-dd";
	private static final long			DAY_IN_MILLI_SECONDS	= (24L * 60L * 60L * 1000L);

	private static HttpTransport		HTTP_TRANSPORT			= null;
	private static final JsonFactory	JSON_FACTORY			= new JacksonFactory();

	private Analytics					googleAnalytics;
	private AnalyticsConfiguration		analyticsConfiguration;

	protected GoogleAnalyticsDataCollector(
			AnalyticsConfiguration analyticsConfiguration)
			throws AnalyticsTechnicalException {
		this.analyticsConfiguration = analyticsConfiguration;
		ControlConfig controlConfig = analyticsConfiguration
				.getControlConfig();
		GAConfiguration gaConfiguration = analyticsConfiguration
				.getGaConfiguration();
		init(gaConfiguration, controlConfig);
	}

	private void init(GAConfiguration gaConfiguration,
			ControlConfig controlConfig) throws AnalyticsTechnicalException {
		LOG.debug("Initializing the GoogleAnalyticsDataCollector...");
		if (HTTP_TRANSPORT == null) {
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
					trim(controlConfig.getHttpProxyIPAddress()),
					controlConfig.getHttpProxyPort()));
			try {
				if ("Y".equals(controlConfig.getHttpProxyUseFlag())) {
					HTTP_TRANSPORT = new NetHttpTransport.Builder()
							.setProxy(proxy)
							.trustCertificates(
									GoogleUtils.getCertificateTrustStore())
							.build();
				} else {
					HTTP_TRANSPORT = new NetHttpTransport.Builder()
							.trustCertificates(
									GoogleUtils.getCertificateTrustStore())
							.build();
				}
				LOG.info("Initialized the HttpTransport and JSON Factory for accessing the GA Engine");
			} catch (GeneralSecurityException e) {
				LOG.error("Error while initializing the collector. Reason="
						+ e.getMessage() + " Cause=" + e.toString());
				throw new AnalyticsTechnicalException(e.getMessage(), e);

			} catch (IOException e) {
				LOG.error("Error while initializing the collector. Reason="
						+ e.getMessage() + " Cause=" + e.toString());
				throw new AnalyticsTechnicalException(e.getMessage(), e);
			} catch (Exception e) {
				LOG.error("Error while initializing the collector. Reason="
						+ e.getMessage() + " Cause=" + e.toString());
				throw new AnalyticsTechnicalException(e.getMessage(), e);
			}
		}
		initializeAnalytics(gaConfiguration);
	}

	/**
	 * Authorizes access to GA Engine.
	 * 
	 * @param gaCredential
	 * @return
	 */
	private Credential authorize(GACredential gaCredential)
			throws AnalyticsTechnicalException {
		GoogleCredential credential = null;
		try {
			credential = new GoogleCredential.Builder()
					.setTransport(HTTP_TRANSPORT)
					.setJsonFactory(JSON_FACTORY)
					.setServiceAccountId(
							trim(gaCredential.getServiceAccountId()))
					.setServiceAccountScopes(
							Collections.singleton(trim(gaCredential
									.getServiceAccountScopes())))
					.setServiceAccountPrivateKeyFromP12File(
							new File(trim(gaCredential
									.getPrivateKeyFileAbsoultePath())))
					.build();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
			throw new AnalyticsTechnicalException(e.getMessage(), e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new AnalyticsTechnicalException(e.getMessage(), e);
		}
		LOG.debug("Authorized to use GA Service");
		return credential;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<? extends T> collect() throws AnalyticsException {
		LOG.info("Collecting data thru GoogleAnalyticsDataCollector...");
		GAConfiguration gaConfig = analyticsConfiguration.getGaConfiguration();
		List<List<String>> result = null;
		for (GAQuery gaQuery : gaConfig.getGaQueries()) {
			try {
				result = executeDataQuery(gaQuery);
			} catch (IOException e) {
				throw new AnalyticsException(e.getMessage(), e);
			} catch (ParseException e) {
				throw new AnalyticsException(e.getMessage(), e);
			}
		}
		LOG.info("Collected data thru GoogleAnalyticsDataCollector...");
		return (Collection<? extends T>) result;
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Collection<? extends T> collect(AnalyticsQuery query)
			throws AnalyticsException {
		Collection<? extends T> result = null;
		try {
			result = (Collection<? extends T>) executeDataQuery((GAQuery) query);
		} catch (IOException e) {
			LOG.error("Error while collecting the data. Reason="
					+ e.getMessage() + " Cause=" + e.toString());
			throw new AnalyticsException(e.getMessage(), e);
		} catch (Exception e) {
			LOG.error("Error while collecting the data. Reason="
					+ e.getMessage() + " Cause=" + e.toString());
			throw new AnalyticsException(e.getMessage(), e);
		}
		return result;
	}

	/**
	 * Constructs and executes GA Query.
	 * 
	 * @param gaQuery
	 * @return
	 * @throws IOException
	 * @throws ParseException
	 */
	private List<List<String>> executeDataQuery(GAQuery gaQuery)
			throws IOException, ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
		String gaEndDate = trim(gaQuery.getGaEndDate());
		String gaStartDate = trim(gaQuery.getGaStartDate());
		long currentTime = System.currentTimeMillis();
		
		if (CURRENT_DATE_LITERAL.equalsIgnoreCase(gaEndDate)
				|| AnalyticsUtil.isEmpty(gaEndDate)) {
			gaEndDate = dateFormat.format(new Date(currentTime));
		}

		if (AnalyticsUtil.isEmpty(gaStartDate)) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date(dateFormat.parse(gaEndDate).getTime()
					- (gaQuery.getGaSweepBackDays() * DAY_IN_MILLI_SECONDS)));
			gaStartDate = dateFormat.format(cal.getTime());
		}
		gaQuery.setGaEndDate(gaEndDate);
		gaQuery.setGaStartDate(gaStartDate);

		LOG.debug("GaStartDate = " + gaStartDate + " GaEndDate = " + gaEndDate);

		Get get = googleAnalytics.data().ga()
				.get("ga:" + trim(gaQuery.getGaProfileId()),
						gaQuery.getGaStartDate(),
						gaQuery.getGaEndDate(), trim(gaQuery.getGaMetrics()));
		get = gaQuery.getGaDimensions() != null
				&& gaQuery.getGaDimensions().length() > 0 ? get
				.setDimensions(trim(gaQuery.getGaDimensions())) : get;
		get = gaQuery.getGaSort() != null
				&& gaQuery.getGaSort().length() > 0 ? get
				.setSort(trim(gaQuery.getGaSort())) : get;
		get = gaQuery.getGaSegment() != null
				&& gaQuery.getGaSegment().length() > 0 ? get
				.setSegment(trim(gaQuery.getGaSegment())) : get;
		get = gaQuery.getGaFilter() != null
				&& gaQuery.getGaFilter().length() > 0 ? get
				.setFilters(trim(gaQuery.getGaFilter())) : get;
		get = get.setMaxResults(gaQuery.getGaMaxResults());
		LOG.info("GA Query = " + get.toString());
		return parseDataFromGA(get.execute(), gaQuery);
	}

	/**
	 * Initializes the GA Engine required for data look up.
	 * 
	 * @param gaConfiguration
	 * @return
	 */
	private void initializeAnalytics(GAConfiguration gaConfiguration)
			throws AnalyticsTechnicalException {
		LOG.debug("Initializing the GA Engine...");
		// Authorization.
		Credential credential = authorize(gaConfiguration.getGaCredential());

		// Set up and return Google Analytics API client.
		googleAnalytics = new Analytics.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				credential)
				.setApplicationName(trim(
						gaConfiguration.getGaCredential()
								.getGaApplicationName())).build();

	}

	/**
	 * Prints the output from the Core Reporting API. The profile name is
	 * printed along with each column name and all the data in the rows.
	 * 
	 * @param results
	 *            data returned from the Core Reporting API.
	 */
	private List<List<String>> parseDataFromGA(GaData results, GAQuery gaQuery) {
		List<List<String>> resultList = new ArrayList<List<String>>();
		if (results.getRows() == null || results.getRows().isEmpty()) {
			LOG.warn("No results Found for query=" + gaQuery.getName());
		} else {
			LOG.debug("Processing the GA data");
			List<String> headerList = new ArrayList<String>();
			for (ColumnHeaders header : results.getColumnHeaders()) {
				headerList.add(header.getName());
			}
			resultList.add(headerList);
			resultList.addAll(results.getRows());
			LOG.info("Number of rows fetched from GA Engine = "
					+ results.size());
			LOG.debug("Completed the processing!");
		}
		return resultList;
	}
}
