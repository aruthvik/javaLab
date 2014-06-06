package com.aravind.ruthvik.analytics.collector;

import java.util.Collection;

import com.aravind.ruthvik.analytics.data.AnalyticsQuery;
import com.aravind.ruthvik.analytics.exception.AnalyticsException;

/**
 * Abstract implementation of {@link AnalyticsDataCollector}.
 * 
 * @author aravind.ruthvik
 * @since Aug 19, 2013 12:39:19 PM 
 * 
 * $Id:$
 */
public abstract class SavvisAnalyticsDataCollector<T> implements
		AnalyticsDataCollector<T> {

	public SavvisAnalyticsDataCollector() {
	}

	protected abstract Collection<? extends T> collect() throws AnalyticsException;

	protected abstract Collection<? extends T> collect(AnalyticsQuery query) throws AnalyticsException;

	@Override
	public Collection<? extends T> doCollect() throws AnalyticsException {
		return collect();
	}

	@Override
	public Collection<? extends T> doCollect(AnalyticsQuery query) throws AnalyticsException {
		return collect(query);
	}
}
