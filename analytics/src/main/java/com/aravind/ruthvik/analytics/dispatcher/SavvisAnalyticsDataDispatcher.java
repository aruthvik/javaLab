package com.aravind.ruthvik.analytics.dispatcher;

import com.aravind.ruthvik.analytics.exception.AnalyticsException;
import com.aravind.ruthvik.analytics.exception.AnalyticsTechnicalException;


public abstract class SavvisAnalyticsDataDispatcher<T> implements AnalyticsDataDispatcher<T> {

	protected abstract void dispatch() throws AnalyticsException, AnalyticsTechnicalException;

	@Override
	public void doDispatch() throws AnalyticsException, AnalyticsTechnicalException{
		dispatch();
	}
}
