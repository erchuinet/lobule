package com.erchuinet.common;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class DateUtil {
    private static Logger logger = LoggerFactory.getLogger(DateUtil.class);
    /**
     * 日期格式YYYYMM
     */
    public static final String  FORMAT_DATE_YYYYMM          = "YYYYMM";
    /**
     * 日期格式yyyy-MM-dd
     */
    public static final String  FORMAT_DATE_YYYY_MM_DD          = "yyyy-MM-dd";
    
    public static final String  FORMAT          = "yyyy年MM月dd日";

    /** 时间格式 */
    public static final String  FORMAT_DATE_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    
    public static final String  START_DATE_YYYY_MM_DD_HH_MM = "yyyy-MM-dd 00:00";
    
    public static final String  END_DATE_YYYY_MM_DD_HH_MM = "yyyy-MM-dd 23:59";
    /** 时间格式-毫秒 */
    public static final String  FORMAT_DATE_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss.SSS";
    
    /** 时间戳 */
    public static final String  FORMAT_DATE_TIMESTAMP = "yyyyMMddHHmmssSSS";

    /**
     * 
     * <p>两日期之间的天数差：date1-date2。</p>
     *
     * @param date1 日期
     * @param date2 日期
     * @return 有符号天数差
     */
    public static int getDaysDiff(Date date1, Date date2) {

        int result = 0;

        final Calendar calendar1 = Calendar.getInstance();
        final Calendar calendar2 = Calendar.getInstance();
        calendar1.setTime(date1);
        calendar2.setTime(date2);

        final int flag = calendar1.compareTo(calendar2);

        if (flag < 0) {
            calendar1.setTime(date2);
            calendar2.setTime(date1);
        }

        result = calendar1.get(Calendar.DAY_OF_YEAR) - calendar2.get(Calendar.DAY_OF_YEAR);

        //不在同一年
        while (calendar1.get(Calendar.YEAR) > calendar2.get(Calendar.YEAR)) {

            result += calendar2.getActualMaximum(Calendar.DAY_OF_YEAR);
            calendar2.add(Calendar.YEAR, 1);
        }

        result = flag < 0 ? -result : result;
        return result;
    }

    /**
     * 计算日期间隔天数
     * @param startDate
     * @param endDate
     * @return
     */
    public static Long getDaysBetween(Date startDate, Date endDate) {  
        Calendar fromCalendar = Calendar.getInstance();  
        fromCalendar.setTime(startDate);  
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);  
        fromCalendar.set(Calendar.MINUTE, 0);  
        fromCalendar.set(Calendar.SECOND, 0);  
        fromCalendar.set(Calendar.MILLISECOND, 0);  
  
        Calendar toCalendar = Calendar.getInstance();  
        toCalendar.setTime(endDate);  
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);  
        toCalendar.set(Calendar.MINUTE, 0);  
        toCalendar.set(Calendar.SECOND, 0);  
        toCalendar.set(Calendar.MILLISECOND, 0);  
  
        return (toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24);  
    }
    
    public static int getDaysDiff(Calendar calendar, Date date) {

        return getDaysDiff(calendar.getTime(), date);
    }


    public static int getDaysDiff(Date date, Calendar calendar) {

        return getDaysDiff(date, calendar.getTime());
    }


    public static int getDaysDiff(Calendar calendar1, Calendar calendar2) {

        return getDaysDiff(calendar1.getTime(), calendar2.getTime());
    }


    /**
     * 
     * <p>两日期之间的天数差：|date1-date2|</p>
     *
     * @param date1 日期
     * @param date2 日期
     * @return 日期天数差绝对值
     */
    public static int getDaysDiffAbs(Date date1, Date date2) {

        return Math.abs(getDaysDiff(date1, date2));
    }


    public static int getDaysDiffAbs(Calendar calendar, Date date) {

        return getDaysDiffAbs(calendar.getTime(), date);
    }


    public static int getDaysDiffAbs(Date date, Calendar calendar) {

        return getDaysDiffAbs(date, calendar.getTime());
    }


    public static int getDaysDiffAbs(Calendar calendar1, Calendar calendar2) {

        return getDaysDiffAbs(calendar1.getTime(), calendar2.getTime());
    }


    /**
     * 
     * <p>向后取num天日期</p>
     *
     * @param date 日期
     * @param num 天数差
     * @return 向后取num天数后日期
     */
    public static Date getDaysAfter(Date date, int num) {

        Date newdate = null;

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_YEAR, num);

        newdate = calendar.getTime();
        return newdate;
    }


    /**
     * 
     * <p>向前取num天日期</p>
     *
     * @param date 日期
     * @param num 天数差
     * @return 向前取num天数后日期
     */
    public static Date getDaysBefore(Date date, int num) {

        return getDaysAfter(date, -num);
    }
    
    /**
     * <p>获取日期字符串。</p>
     *
     * @param date 日期
     * @return 日期
     */
    public static String getSystemDateYYYYMM() {
    	final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE_YYYYMM);
        return simpleDateFormat.format(new Date());
    }
    
    /**
     * <p>获取日期字符串。</p>
     *
     * @param date 日期
     * @return 日期
     */
    public static String getStringDate(final Date date) {

        try {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE_YYYY_MM_DD);
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            logger.debug("日期转换出错"+e);
            return null;
        }
    }
    public static String getStringFormatDate(final Date date,String format) {

        try {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
            return simpleDateFormat.format(date);
        } catch (Exception e) {
        	logger.debug("日期转换出错"+e);
            return null;
        }
    }

    /**
     * <p>获取日期。</p>
     *
     * @param date 字符型日期
     * @return 日期
     */
    public static Date getDate(final String date) {

        try {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE_YYYY_MM_DD);
            return simpleDateFormat.parse(date);
        } catch (ParseException e) {
        	logger.debug("日期转换出错"+e);
            return null;
        }
    }


    /**
     * <p>获取时间字符串。</p>
     *
     * @param time 时间
     * @return 时间
     */
    public static String getStringDateTime(final Date time) {

        try {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE_YYYY_MM_DD_HH_MM_SS);
            return simpleDateFormat.format(time);
        } catch (Exception e) {
        	logger.debug("日期转换出错"+e);
            return null;
        }
    }
    
    /**
     * <p>获取时间字符串。</p>
     *
     * @param time 时间
     * @return 时间
     */
    public static String getStringDateTimeMillisecond (final Date time) {

        try {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE_YYYY_MM_DD_HH_MM_SS_SSS);
            return simpleDateFormat.format(time);
        } catch (Exception e) {
        	logger.debug("日期转换出错"+e);
            return null;
        }
    }


    /**
     * <p>获取时间。</p>
     *
     * @param time 字符型时间
     * @return 时间
     */
    public static Date getDateTime(final String time) {

        try {
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE_YYYY_MM_DD_HH_MM_SS);
            return simpleDateFormat.parse(time);
        } catch (ParseException e) {
        	logger.debug("日期转换出错"+e);
            return null;
        }
    }

	/**
	 * 得到当前时间戳
	 * @return
	 * @author
	 */
	public static String getCurrentTimeStamp(){
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE_TIMESTAMP);
        return simpleDateFormat.format(new Date());
	}
	
	/**
	 * 得到当前日期
	 * @return
	 * @author
	 */
	public static String getCurrentDate(){
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(FORMAT_DATE_YYYY_MM_DD);
		return simpleDateFormat.format(new Date());
	}
	public static String getCurrentDate(String format){
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(new Date());
	}
	public static Date getNextDay(Date date,int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, +n);
        date = calendar.getTime();
        return date;
    }
	public static String getWeekOfDate(Date date) {      
	    String[] weekOfDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};        
	    Calendar calendar = Calendar.getInstance();      
	    if(date != null){        
	         calendar.setTime(date);      
	    }        
	    int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;      
	    if (w < 0){        
	        w = 0;      
	    }      
	    return weekOfDays[w];    
	}
	
	/**
	 * 计算时间间隔
	 * 格式   HH:mm
	 * @param str1
	 * @param str2
	 * @return 返回时间间隔	eg:1小时15分钟    or  15分钟
	 */
	public static String getDistanceTime(String str1, String str2) {  
        DateFormat df = new SimpleDateFormat("HH:mm");  
        Date one;  
        Date two;  
        long hour = 0;  
        long min = 0;  
        try {
            one = df.parse(str1);
            two = df.parse(str2);
            long time1 = one.getTime();  
            long time2 = two.getTime();  
            long diff ;  
            if(time1<time2) {  
                diff = time2 - time1;  
            } else {  
                diff = time1 - time2;  
            }  
            hour = (diff / (60 * 60 * 1000) - (diff / (24 * 60 * 60 * 1000)) * 24);  
            min = ((diff / (60 * 1000)) - (diff / (24 * 60 * 60 * 1000)) * 24 * 60 - hour * 60);  
        } catch (ParseException e) {  
            e.printStackTrace();  
        }  
        return hour!=0?hour + "小时" + min + "分钟":min + "分钟";  
    }
}
