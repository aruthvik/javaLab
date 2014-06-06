package com.aravind.ruthvik.analytics.collector;

import com.aravind.ruthvik.analytics.configuration.AnalyticsConfiguration;
import com.aravind.ruthvik.analytics.data.AnalyticsProvider;
import com.aravind.ruthvik.analytics.exception.AnalyticsTechnicalException;

/**
 * A factory implementation to produce the data collectors.
 *  
 * @author aravind.ruthvik
 * @since Aug 20, 2013 9:32:43 AM
 * $Id:$
 */
public class SavvisAnalyticsDataCollectorFactory {

	@SuppressWarnings("rawtypes")
	public static SavvisAnalyticsDataCollector<?> getCollector(
			AnalyticsProvider provider, AnalyticsConfiguration config) throws AnalyticsTechnicalException {
		SavvisAnalyticsDataCollector<?> collector = null;
		switch (provider) {
			case GOOGLE:
				collector = new GoogleAnalyticsDataCollector(config);
				break;
		}
		return collector;
	}

	
	private SavvisAnalyticsDataCollectorFactory() {
	}
}
