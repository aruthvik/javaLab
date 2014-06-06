package com.aravind.ruthvik.analytics.util;

public class AnalyticsUtil {

	private AnalyticsUtil() {

	}

	/**
	 * Removes space, newline and tabs (any invisible chars).
	 * 
	 * @param toBeTrimmed
	 * @return
	 */
	public static String trim(String toBeTrimmed) {
		return (toBeTrimmed != null) ? toBeTrimmed.trim().replaceAll(
				"\\n", "").replaceAll("\\r", "").replaceAll("\\t", "")
				: toBeTrimmed;
	}
	
	public static boolean isEmpty(String value){
		return value == null || trim(value).length() == 0;
	}

}
