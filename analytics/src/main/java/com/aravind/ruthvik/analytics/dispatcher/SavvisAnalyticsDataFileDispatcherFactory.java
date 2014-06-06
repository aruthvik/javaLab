/**
 * 
 */
package com.aravind.ruthvik.analytics.dispatcher;

import java.util.Collection;

import com.aravind.ruthvik.analytics.data.Dispatcher;

/**
 * @author aravind.ruthvik
 * @since Aug 20, 2013 11:53:37 AM $Id:$
 * 
 */
public class SavvisAnalyticsDataFileDispatcherFactory {

	/**
	 * Returns the dispatcher instance
	 * 
	 * @param provider
	 * @param outputFilePath
	 * @param dispatchData
	 * @return
	 */
	public static <T> SavvisAnalyticsDataDispatcher<?> getDispatcher(
			Dispatcher provider, String outputFilePath,
			Collection<? extends T> dispatchData) {
		SavvisAnalyticsDataDispatcher<?> dispatcher = null;
		switch (provider) {
			case GOOGLE_FILE_CSV:
				dispatcher = new GoogleAnalyticsDataCSVFileDispatcher<T>(
						outputFilePath, dispatchData);
				break;
		}
		return dispatcher;
	}

	/**
	 * Private constructor
	 */
	private SavvisAnalyticsDataFileDispatcherFactory() {
	}
}
