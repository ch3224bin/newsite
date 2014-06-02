package com.john_na.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class DateUtil {
	
	public static String date2String(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	public static String date2String(long ms, String pattern) {
		Date date = new Date(ms);
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	public static String date2String(DateTime dateTime, String pattern) {
		DateTimeFormatter dtfOut = DateTimeFormat.forPattern(pattern);
		return dtfOut.print(dateTime);
	}
	
	public static Date string2Date(String date, String pattern) throws Exception {
		if (StringUtils.isEmpty(date)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.parse(date);
	}
	
	public static String getCurrentDay(String pattern) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(new Date());
	}

	public static Date firstDayOfWeek(Date date) {
		Calendar c = Calendar.getInstance(Locale.KOREA);
		c.setTime(date);
	    while (c.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
	        c.add(Calendar.DATE, -1);
	    }
	    
		return c.getTime();
	}
	
	public static Date lastDayOfWeek(Date date) {
		Calendar c = Calendar.getInstance(Locale.KOREA);
		c.setTime(date);
		while (c.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
			c.add(Calendar.DATE, 1);
		}
		
		return c.getTime();
	}
	
	
}
