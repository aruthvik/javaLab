package com.aravind.ruthvik.analytics.exception;

public class AnalyticsTechnicalException extends Exception {
	
	private static final long	serialVersionUID	= -1250230612420171191L;

	/**
	 * 
	 */
	public AnalyticsTechnicalException() {
		super();

	}

	/**
	 * @param message
	 * @param cause
	 */
	public AnalyticsTechnicalException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public AnalyticsTechnicalException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public AnalyticsTechnicalException(Throwable cause) {
		super(cause);
	}
}
