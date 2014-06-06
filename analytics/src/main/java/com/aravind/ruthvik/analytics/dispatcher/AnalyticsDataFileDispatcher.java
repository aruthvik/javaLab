package com.aravind.ruthvik.analytics.dispatcher;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;

import com.aravind.ruthvik.analytics.exception.AnalyticsException;
import com.aravind.ruthvik.analytics.exception.AnalyticsTechnicalException;

/**
 * 
 * @author aravind.ruthvik
 * @since Aug 19, 2013 9:58:24 PM $Id:$
 * 
 * @param <T>
 */
public abstract class AnalyticsDataFileDispatcher<T> extends SavvisAnalyticsDataDispatcher<T> {

	private static final Logger	LOG	= Logger.getLogger(AnalyticsDataFileDispatcher.class);

	/** Output File Path */
	private String				outputFilePath;

	/**
	 * Constructs the dispatcher
	 * 
	 * @param outputFilePath
	 * @param dispatchData
	 */
	protected AnalyticsDataFileDispatcher(String outputFilePath) {
		this.outputFilePath = outputFilePath;
	}

	/**
	 * Abstract implementation
	 * 
	 * (non-Javadoc)
	 * 
	 * @see com.aravind.ruthvik.analytics.dispatcher.SavvisAnalyticsDataDispatcher#dispatch()
	 */
	@Override
	protected void dispatch() throws AnalyticsException,
			AnalyticsTechnicalException {
		performDispatch();
	}

	/**
	 * Implementors should implement this operation.
	 * 
	 * @throws AnalyticsException
	 * @throws AnalyticsTechnicalException
	 */
	protected abstract void performDispatch() throws AnalyticsException,
			AnalyticsTechnicalException;

	/**
	 * Writes to a file.
	 * 
	 * @param lines
	 * @throws AnalyticsTechnicalException
	 */
	protected void writeToFile(List<StringBuffer> lines)
			throws AnalyticsTechnicalException {
		LOG.info("Writing the analytics data to file " + this.outputFilePath);
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(
					new File(this.outputFilePath)));
			for (StringBuffer aLine : lines) {
				bw.write(aLine.toString());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new AnalyticsTechnicalException(e.getMessage(), e);
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}
