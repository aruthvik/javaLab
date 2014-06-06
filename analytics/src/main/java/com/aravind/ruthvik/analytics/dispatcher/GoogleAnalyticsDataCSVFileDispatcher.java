package com.aravind.ruthvik.analytics.dispatcher;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;

import com.aravind.ruthvik.analytics.exception.AnalyticsException;
import com.aravind.ruthvik.analytics.exception.AnalyticsTechnicalException;

/**
 * 
 * @author aravind.ruthvik
 * @since Aug 20, 2013 11:53:07 AM $Id:$
 * 
 * @param <T>
 */
public class GoogleAnalyticsDataCSVFileDispatcher<T> extends
		AnalyticsDataFileDispatcher<T> {

	private static final Logger		LOG				= Logger.getLogger(GoogleAnalyticsDataCSVFileDispatcher.class);

	private static final String		DELIMITER		= ",";
	private static final String		LINE_SEPARATOR	= System.getProperty("line.separator");
	private Collection<? extends T>	dispatchData;

	protected GoogleAnalyticsDataCSVFileDispatcher(String outputFilePath,
			Collection<? extends T> dispatchData) {
		super(outputFilePath);
		this.dispatchData = dispatchData;
	}

	public Collection<? extends T> getDispatchData() {
		return dispatchData;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected void performDispatch() throws AnalyticsException,
			AnalyticsTechnicalException {
		LOG.info("Performing the dispatch of analytics data");
		List<List<String>> toBeDispatched = (List<List<String>>) this.dispatchData;
		List<StringBuffer> lines = new ArrayList<StringBuffer>();
		for (List<String> aRow : toBeDispatched) {
			StringBuffer row = new StringBuffer();
			for (String aColumn : aRow) {
				row.append(aColumn).append(DELIMITER);
			}
			row.append(LINE_SEPARATOR);
			lines.add(row);
		}
		writeToFile(lines);
	}

	public void setDispatchData(Collection<? extends T> dispatchData) {
		this.dispatchData = dispatchData;
	}

}
