package com.aravind.ruthvik.analytics.exception;

/**
 * Generic Analytics Exception 
 * 
 * @author aravind.ruthvik
 * @since Aug 20, 2013 2:36:33 PM
 * $Id:$
 * 
 */
public class AnalyticsException extends Exception {

	private static final long	serialVersionUID	= 6935640139763503615L;

	/**
	 * 
	 */
	public AnalyticsException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public AnalyticsException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public AnalyticsException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public AnalyticsException(Throwable cause) {
		super(cause);
	}

}
