package com.aravind.ruthvik.analytics.configuration;

import java.io.Serializable;

import com.aravind.ruthvik.analytics.data.LogLevel;
import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * 
 * @author aravind.ruthvik
 * @since Aug 19, 2013 11:16:06 AM $Id:$
 */
@XStreamAlias("controlConfig")
public class ControlConfig implements Serializable {

	/**
	 * 
	 */
	private static final long	serialVersionUID	= 3677076708906411004L;
	/** Proxy IP Address */
	private String				httpProxyIPAddress;
	/** Proxy Port */
	private int					httpProxyPort;
	/** Proxy Flag = Y or N */
	private String				httpProxyUseFlag;
	/** Log4j Log Level */
	private LogLevel			logLevel;

	/**
	 * 
	 */
	public ControlConfig() {
		super();
	}

	/**
	 * 
	 * @param logLevel
	 * @param httpProxyUseFlag
	 * @param httpProxyIPAddress
	 * @param httpProxyPort
	 */
	public ControlConfig(LogLevel logLevel, String httpProxyUseFlag,
			String httpProxyIPAddress, int httpProxyPort) {
		super();
		this.logLevel = logLevel;
		this.httpProxyUseFlag = (httpProxyUseFlag);
		this.httpProxyIPAddress = httpProxyIPAddress;
		this.httpProxyPort = httpProxyPort;
	}

	public String getHttpProxyIPAddress() {
		return httpProxyIPAddress;
	}

	public int getHttpProxyPort() {
		return httpProxyPort;
	}

	public String getHttpProxyUseFlag() {
		return httpProxyUseFlag;
	}

	public LogLevel getLogLevel() {
		return logLevel;
	}

	public void setHttpProxyIPAddress(String httpProxyIPAddress) {
		this.httpProxyIPAddress = httpProxyIPAddress;
	}

	public void setHttpProxyPort(int httpProxyPort) {
		this.httpProxyPort = httpProxyPort;
	}

	public void setHttpProxyUseFlag(String httpProxyUseFlag) {
		this.httpProxyUseFlag = httpProxyUseFlag;
	}

	public void setLogLevel(LogLevel logLevel) {
		this.logLevel = logLevel;
	}

}
