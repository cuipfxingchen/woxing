/**
 * Project Name:taxi_order
 * File Name:DateFormatUtil.java
 * Package Name:com.hdsx.utils
 * Date:2013-7-22上午9:52:56
 * Copyright (c) 2013, Kevin All Rights Reserved.
 *
 */

package com.hdsx.taxi.woxing.mqutil.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName:DateFormatUtil <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2013-7-22 上午9:52:56 <br/>
 * 
 * @author Kevin
 * @version
 * @see
 */
public class DateFormatUtil {
	static final SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	
	/**
	 * 
	 * date2MySQLDateTimeString:Java.util.Date转换MySQLDateTime类型方法.
	 * 
	 * TODO 将Java.util.Date类型转换为MySQLDateTime得字符串类型.
	 * 
	 * date 待转换的java.util.date类型date.
	 */
	public static String date2MySQLDateTimeString(Date date) {
		if (null == date) return "";
		return dateformat.format(date);
	}

	public static Date dateString2JavaUtilDate(String datestr) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
		Date date = null;
		try {
			if (datestr.length() == 19) {
				date = sdf.parse(datestr);
			}
		} catch (ParseException e) {
			e.printStackTrace();

		}
		return date;
	}

	/*
	 * 2013-07-26 14:31:14
	 */
	public static String getMonth(String dateString) {
		String[] str = dateString.split("-");
		/* get month */
		String month = str[1];
		if (month.startsWith("0")) {
			month.replace("0", "");
		}
		return month;
	}

	/*
	 * 2013-07-26 14:31:14
	 */
	public static String getDay(String dateString) {
		String[] str = dateString.trim().split(" ");
		/* get day */
		String YMDString = str[0];
		String[] YMDstr = YMDString.split("-");
		String day = YMDstr[2];
		if (day.startsWith("0")) {
			day.replace("0", "");
		}
		return day;
	}

	public static String getTime(String dateString) {
		String[] str = dateString.split(" ");
		String timeString = str[1];
		String[] timeStr = timeString.trim().split(":");
		/* get day */
		String h = timeStr[0];
		String m = timeStr[1];
		String s = timeStr[2];
		if (h.startsWith("0")) {
			h.replace("0", "");
		}
		if (m.startsWith("0")) {
			m.replace("0", "");
		}
		if (s.startsWith("0")) {
			s.replace("0", "");
		}
		// String time = h + "点" + m + "分"+s+"秒";
		String time = h + "点" + m + "分";
		return time;
	}

	/* Hour */
	public static String getHour(String dateString) {
		String[] str = dateString.split(" ");
		String timeString = str[1];
		String[] timeStr = timeString.trim().split(":");
		/* get day */
		String h = timeStr[0];
		if (h.startsWith("0")) {
			h.replace("0", "");
		}

		String time = h + "时";
		return time;
	}

	/* Minute */
	public static String getMinute(String dateString) {
		String[] str = dateString.split(" ");
		String timeString = str[1];
		String[] timeStr = timeString.trim().split(":");
		/* get day */
		String m = timeStr[1];
		// String s = timeStr[2];
		if (m.startsWith("0")) {
			m.replace("0", "");
		}
		// String time = h + "点" + m + "分"+s+"秒";
		String time = m + "分";
		return time;
	}

	public static void main(String[] args) {
		/*
		 * date2MySQLDateTimeString-----Test
		 */
		// Date date = new Date();
		// System.err.println(date);

		// System.err.println(date2MySQLDateTimeString(date));
		/*
		 * dateString2JavaUtilDate-----Test
		 */
		// String a = "2013-07-22-13-14-15";
		// System.out.println(dateString2JavaUtilDate(a));
		// String dateString = "2013-07-26-14-31-14";
		// getMonth(dateString);
		// Date a = new Date();
		// System.err.println(a);
		/* getDay */
		// String dateString = date2MySQLDateTimeString(date);
		// System.err.println(getDay(dateString));
		/* getTime */
		// System.err.println(getTime(dateString));

	}

}
