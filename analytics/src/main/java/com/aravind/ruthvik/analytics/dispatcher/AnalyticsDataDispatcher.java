package com.aravind.ruthvik.analytics.dispatcher;

import com.aravind.ruthvik.analytics.exception.AnalyticsException;
import com.aravind.ruthvik.analytics.exception.AnalyticsTechnicalException;

/**
 * Dispatcher Interface
 * 
 * @author aravind.ruthvik
 * @since Aug 20, 2013 11:39:37 AM
 * $Id:$
 *
 * @param <T>
 */
public interface AnalyticsDataDispatcher<T> {
	public void doDispatch() throws AnalyticsException, AnalyticsTechnicalException;
}
