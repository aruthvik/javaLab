package com.aravind.ruthvik.analytics.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * 
 * @author aravind.ruthvik
 * @since Aug 19, 2013 12:36:48 PM $Id:$
 */
@XStreamAlias("analyticsConfigurations")
public class SavvisAnalyticsConfiguration implements Serializable {
		
	private static final long serialVersionUID = 2791135162194191121L;
	
	/** All the analytics configuration holder **/
	@XStreamImplicit(itemFieldName="analyticsConfiguration")
	private List<AnalyticsConfiguration> analyticsConfiguration = new ArrayList<AnalyticsConfiguration>();	

	/**
	 * @param analyticsConfigurations
	 */
	public SavvisAnalyticsConfiguration(List<AnalyticsConfiguration> analyticsConfigurations) {
		this.analyticsConfiguration = analyticsConfigurations;
	}

	public List<AnalyticsConfiguration> getAnalyticsConfigurations() {
		return analyticsConfiguration;
	}

	public void setAnalyticsConfigurations(
			List<AnalyticsConfiguration> analyticsConfigurations) {
		this.analyticsConfiguration = analyticsConfigurations;
	}

}
