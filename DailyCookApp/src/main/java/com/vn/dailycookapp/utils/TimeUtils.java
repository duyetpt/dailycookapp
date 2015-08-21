package com.vn.dailycookapp.utils;

import java.util.Calendar;
import java.util.TimeZone;

public class TimeUtils {
	
	private static final String		STANDARD_TIME_ZONE	= "Etc/GMT+0";
//	private static final String     VN_TIME_ZONE = "Etc/GMT+7";
	
	private static final Calendar	standardInstance	= Calendar
																.getInstance(TimeZone.getTimeZone(STANDARD_TIME_ZONE));
	
	public static long getCurrentGMTTime() {
		standardInstance.getTimeInMillis();
		TimeZone timezone = TimeZone.getTimeZone(STANDARD_TIME_ZONE);
		long currentTime = System.currentTimeMillis();
		return currentTime - timezone.getOffset(currentTime);
	}
}
