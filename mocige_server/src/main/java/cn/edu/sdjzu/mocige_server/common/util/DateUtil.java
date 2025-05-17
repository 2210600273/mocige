package cn.edu.sdjzu.mocige_server.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具类
 */
public class DateUtil {

    /**
     * 日期格式：yyyy-MM-dd
     */
    public static final String DATE_PATTERN = "yyyy-MM-dd";
    
    /**
     * 日期时间格式：yyyy-MM-dd HH:mm:ss
     */
    public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    
    /**
     * 获取当前日期
     */
    public static Date getCurrentDate() {
        return new Date();
    }
    
    /**
     * 获取当前时间戳（毫秒）
     */
    public static long getCurrentTimestamp() {
        return System.currentTimeMillis();
    }
    
    /**
     * 日期格式化为字符串
     * 
     * @param date 日期
     * @param pattern 格式
     * @return 格式化后的日期字符串
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        return formatter.format(date);
    }
    
    /**
     * 字符串解析为日期
     * 
     * @param dateStr 日期字符串
     * @param pattern 格式
     * @return 解析后的日期
     */
    public static Date parse(String dateStr, String pattern) {
        if (dateStr == null || dateStr.isEmpty()) {
            return null;
        }
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(pattern);
            return formatter.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException("日期解析异常", e);
        }
    }
    
    /**
     * 日期加减天数
     * 
     * @param date 日期
     * @param days 天数（正数为加，负数为减）
     * @return 计算后的日期
     */
    public static Date addDays(Date date, int days) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }
    
    /**
     * 日期加减月数
     * 
     * @param date 日期
     * @param months 月数（正数为加，负数为减）
     * @return 计算后的日期
     */
    public static Date addMonths(Date date, int months) {
        if (date == null) {
            return null;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }
    
    /**
     * 计算两个日期之间的天数差
     * 
     * @param date1 日期1
     * @param date2 日期2
     * @return 天数差
     */
    public static int daysBetween(Date date1, Date date2) {
        if (date1 == null || date2 == null) {
            return 0;
        }
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        long diff = Math.abs(time1 - time2);
        return (int) (diff / (24 * 60 * 60 * 1000));
    }
}