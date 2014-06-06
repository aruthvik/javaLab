package com.aravind.ruthvik.analytics.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author aravind.ruthvik
 * @since Aug 19, 2013 12:05:07 PM $Id:$
 */
@XStreamAlias("gaConfiguration")
public class GAConfiguration implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6929646588807033428L;

	/** GA Credential */
	private GACredential gaCredential;

	/** GA Data Queries holder */
	private List<GAQuery> gaQueries = new ArrayList<GAQuery>();

	/**
	 * 
	 */
	public GAConfiguration() {
		super();
	}

	/**
	 * @param gaCredential
	 * @param gaQueries
	 */
	public GAConfiguration(GACredential gaCredential, List<GAQuery> gaQueries) {
		this.gaCredential = gaCredential;
		this.gaQueries = gaQueries;
	}

	public GACredential getGaCredential() {
		return gaCredential;
	}

	public List<GAQuery> getGaQueries() {
		return gaQueries;
	}

	public void setGaCredential(GACredential gaCredential) {
		this.gaCredential = gaCredential;
	}

	public void setGaQueries(List<GAQuery> gaQueries) {
		this.gaQueries = gaQueries;
	}

}
