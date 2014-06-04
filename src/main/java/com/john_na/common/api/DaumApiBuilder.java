package com.john_na.common.api;

public class DaumApiBuilder {
	
	private static String CALENDAR_CONSUMER_KEY = DaumApiBundle.getValue("CALENDAR_CONSUMER_KEY");
	private static String CALENDAR_CONSUMER_SECRET_KEY = DaumApiBundle.getValue("CALENDAR_CONSUMER_SECRET_KEY");

	private static String PROFILE_CONSUMER_KEY = DaumApiBundle.getValue("PROFILE_CONSUMER_KEY");
	private static String PROFILE_CONSUMER_SECRET_KEY = DaumApiBundle.getValue("PROFILE_CONSUMER_SECRET_KEY");
	
	public static DaumApi getProfileApi() {
		return new DaumApi(PROFILE_CONSUMER_KEY, PROFILE_CONSUMER_SECRET_KEY);
	}
	
	public static DaumApi getCalendarApi() {
		return new DaumApi(CALENDAR_CONSUMER_KEY, CALENDAR_CONSUMER_SECRET_KEY);
	}
}
