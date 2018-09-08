package com.lqinggang.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author LQingGang
 * @time 2018年4月29日 - 下午6:34:50
 */
public class DateUtil {

	/**
	 * 与当前系统时间的时间差
	 * 
	 * @param date
	 * @return String
	 */
	public static String getStateTime(int timeDifference) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, timeDifference);
		java.util.Date monday = calendar.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = df.format(monday);
		return preMonday;
	}

	public static String getDailyTimeStart(int timeDifference) {
		return getStateTime(timeDifference) + " 00:00:00";
	}

	public static String getDailyTimeEnd(int timeDifference) {
		return getStateTime(timeDifference) + " 23:59:59";
	}

}
