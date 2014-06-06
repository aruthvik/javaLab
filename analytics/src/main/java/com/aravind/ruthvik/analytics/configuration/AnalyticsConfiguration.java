/**
 * 
 */
package com.aravind.ruthvik.analytics.configuration;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

/**
 * 
 * @author aravind.ruthvik
 * @since Aug 16, 2013 2:27:48 PM
 * @version 1.0
 * 
 */
@XStreamAlias("analyticsConfiguration")
public class AnalyticsConfiguration implements Serializable {
	
	private static final long serialVersionUID = -7616261054160700762L;

	/** Control Configuration */
	private ControlConfig controlConfig;

	/** Config Type **/
	@XStreamAsAttribute
	private String credentialType;

	/** GAConfiguration Details */
	private GAConfiguration gaConfiguration;

	/** Config name **/
	@XStreamAsAttribute
	private String name;

	/**
	 * 
	 */
	public AnalyticsConfiguration() {
		super();
	}

	/**
	 * @param credentialType
	 * @param name
	 * @param controlConfig
	 * @param gaConfiguration
	 */
	public AnalyticsConfiguration(String credentialType, String name,
			ControlConfig controlConfig, GAConfiguration gaConfiguration) {
		super();
		this.credentialType = credentialType;
		this.name = name;
		this.controlConfig = controlConfig;
		this.gaConfiguration = gaConfiguration;
	}

	public ControlConfig getControlConfig() {
		return controlConfig;
	}

	public String getCredentialType() {
		return credentialType;
	}

	public GAConfiguration getGaConfiguration() {
		return gaConfiguration;
	}

	public String getName() {
		return name;
	}

	public void setControlConfig(ControlConfig controlConfig) {
		this.controlConfig = controlConfig;
	}

	public void setCredentialType(String credentialType) {
		this.credentialType = credentialType;
	}

	public void setGaConfiguration(GAConfiguration gaConfiguration) {
		this.gaConfiguration = gaConfiguration;
	}

	public void setName(String name) {
		this.name = name;
	}

}
