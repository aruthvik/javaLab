package com.aravind.ruthvik.analytics.configuration;

import java.io.Serializable;

import com.aravind.ruthvik.analytics.data.AnalyticsQuery;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 
 * @author aravind.ruthvik
 * @since Aug 19, 2013 12:07:15 PM $Id:$
 */
@XStreamAlias("gaQuery")
public class GAQuery implements AnalyticsQuery, Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 374200070170615592L;

	/**
	 * The dimension data to be retrieved from the API. A single request is
	 * limited to a maximum of 7 dimensions.
	 */
	private String				gaDimensions;

	/** GA End Date */
	private String				gaEndDate;

	/**
	 * Specifies a subset of all data matched in analytics. This is
	 * <code>optional</code>.
	 */
	private String				gaFilter;

	/**
	 * Maximum number of records which need to fetched as part of the data
	 * mining
	 */
	private int					gaMaxResults;

	/**
	 * The metrics data to be retrieved from the API. A single request is
	 * limited to a maximum of 10 metrics.
	 */
	private String				gaMetrics;

	/** GA Profile for which data is queried */
	private String				gaProfileId;

	/** Output flat file path to which data will be written to **/
	private String				gaQueryOutputFilepath;

	/**
	 * Specifies a subset of visits based on either an expression or a filter.
	 * The subset of visits matched happens before dimensions and metrics are
	 * calculated. This is <code>optional</code>.
	 * 
	 */
	private String				gaSegment;

	/**
	 * The order and direction to retrieve the results. Can have multiple
	 * dimensions and metrics. This is <code>optional</code>.
	 */
	private String				gaSort;

	/** GA Start Date */
	private String				gaStartDate;

	/** Sweep back days from the end date **/
	private int					gaSweepBackDays;

	/** Query Internal Id **/
	@XStreamAsAttribute
	private String				id;

	/** Query Internal Name **/
	@XStreamAsAttribute
	private String				name;

	/**
	 * 
	 */
	public GAQuery() {
		super();
	}

	/**
	 * @param gaDimensions
	 * @param gaEndDate
	 * @param gaFilter
	 * @param gaMaxResults
	 * @param gaMetrics
	 * @param gaProfileId
	 * @param gaQueryOutputFilepath
	 * @param gaSegment
	 * @param gaSort
	 * @param gaStartDate
	 * @param gaSweepBackDays
	 * @param id
	 * @param name
	 */
	public GAQuery(String gaDimensions, String gaEndDate, String gaFilter,
			int gaMaxResults, String gaMetrics, String gaProfileId,
			String gaQueryOutputFilepath, String gaSegment, String gaSort,
			String gaStartDate, int gaSweepBackDays, String id, String name) {
		this.gaDimensions = gaDimensions;
		this.gaEndDate = gaEndDate;
		this.gaFilter = gaFilter;
		this.gaMaxResults = gaMaxResults;
		this.gaMetrics = gaMetrics;
		this.gaProfileId = gaProfileId;
		this.gaQueryOutputFilepath = gaQueryOutputFilepath;
		this.gaSegment = gaSegment;
		this.gaSort = gaSort;
		this.gaStartDate = gaStartDate;
		this.gaSweepBackDays = gaSweepBackDays;
		this.id = id;
		this.name = name;
	}

	public String getGaDimensions() {
		return gaDimensions;
	}

	public String getGaEndDate() {
		return gaEndDate;
	}

	public String getGaFilter() {
		return gaFilter;
	}

	public int getGaMaxResults() {
		return gaMaxResults;
	}

	public String getGaMetrics() {
		return gaMetrics;
	}

	public String getGaProfileId() {
		return gaProfileId;
	}

	public String getGaQueryOutputFilepath() {
		return gaQueryOutputFilepath;
	}

	public String getGaSegment() {
		return gaSegment;
	}

	public String getGaSort() {
		return gaSort;
	}

	public String getGaStartDate() {
		return gaStartDate;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setGaDimensions(String gaDimensions) {
		this.gaDimensions = gaDimensions;
	}

	public void setGaEndDate(String gaEndDate) {
		this.gaEndDate = gaEndDate;
	}

	public void setGaFilter(String gaFilter) {
		this.gaFilter = gaFilter;
	}

	public void setGaMaxResults(int gaMaxResults) {
		this.gaMaxResults = gaMaxResults;
	}

	public void setGaMetrics(String gaMetrics) {
		this.gaMetrics = gaMetrics;
	}

	public void setGaProfileId(String gaProfileId) {
		this.gaProfileId = gaProfileId;
	}

	public void setGaQueryOutputFilepath(String gaQueryOutputFilepath) {
		this.gaQueryOutputFilepath = gaQueryOutputFilepath;
	}

	public void setGaSegment(String gaSegment) {
		this.gaSegment = gaSegment;
	}

	public void setGaSort(String gaSort) {
		this.gaSort = gaSort;
	}

	public void setGaStartDate(String gaStartDate) {
		this.gaStartDate = gaStartDate;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGaSweepBackDays() {
		return gaSweepBackDays;
	}

	public void setGaSweepBackDays(int gaSweepBackDays) {
		this.gaSweepBackDays = gaSweepBackDays;
	}

}
