package com.lpt.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static SimpleDateFormat dfTime=new SimpleDateFormat("HH:mm:ss");
	/**
	 * 返回当期日期的字符串
	 * 
	 * @return
	 */
	public static String getNowDateString() {
		return getDateStringToDb(new Date());
	}
	/**
	 * 将日期格式转换为String类型，并填充
	 *
	 * @param date
	 * @return 将日期格式转换为String类型，并填充。例如：2019-5-01 ---> 2019-05-01
	 */
	public static String getDateStringToDb(Date date) {
		return df.format(date);
		
	}
	public static Integer getDatePoor(String endDate, String startDate) {
		int hours=0;
		try{
			String fromDate = df.format(startDate); 
			String toDate = df.format(endDate); 
			long from = df.parse(fromDate).getTime(); 
			long to = df.parse(toDate).getTime(); 
			 hours = (int) ((to - from)/(1000 * 60 * 60));
		}catch(Exception e){
		}
		return hours;
	}
	public static String getNowTime() {
		return dfTime.format(new Date());
	}
}
