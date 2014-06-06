package com.aravind.ruthvik.analytics.collector;

import java.util.Collection;

import com.aravind.ruthvik.analytics.data.AnalyticsQuery;
import com.aravind.ruthvik.analytics.exception.AnalyticsException;
import com.aravind.ruthvik.analytics.exception.AnalyticsTechnicalException;

/**
 * Data collector interface.
 * 
 * @author aravind.ruthvik
 * 
 * @since Aug 19, 2013 1:47:24 PM $Id:$
 */
public interface AnalyticsDataCollector<T> {
	/**
	 * Collects all the data of all the queries mentioned the given
	 * configuration file.
	 * 
	 * @return
	 */
	public Collection<? extends T> doCollect() throws AnalyticsTechnicalException, AnalyticsException;

	/**
	 * Collects the data for the analytical query
	 * 
	 * @param query
	 * @return
	 */
	public Collection<? extends T> doCollect(AnalyticsQuery query) throws AnalyticsTechnicalException, AnalyticsException;
}
