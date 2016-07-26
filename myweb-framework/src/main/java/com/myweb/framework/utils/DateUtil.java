package com.myweb.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by rola
 */
public class DateUtil {

    public static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

    public static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

    /**
     * 格式化日期
     * @param timestamp
     * @return
     */
    public static String formatDate(long timestamp) {
        return dateFormat.format(new Date(timestamp));
    }

    /**
     * 格式化日期和时间
     * @param timestamp
     * @return
     */
    public static String formatDateTime(long timestamp) {
        return datetimeFormat.format(new Date(timestamp));
    }

    /**
     * 格式化时间
     * @param timestamp
     * @return
     */
    public static String formatTime(long timestamp) {
        return timeFormat.format(new Date(timestamp));
    }

    /**
     * 获取当前日期和时间
     * @return
     */
    public static String getCurrentDateTime() {
        return datetimeFormat.format(new Date());
    }

    /**
     * 获取当前日期
     * @return
     */
    public static String getCurrentDate() {
        return dateFormat.format(new Date());
    }
    /**
     * 获取当前时间
     */
    public static String getCurrentTime() {
        return timeFormat.format(new Date());
    }

    /**
     * 解析日期和时间
     * @param str
     * @return
     */
    public static Date parseDateTime(String str) {
        Date date = null;
        try {
            date = datetimeFormat.parse(str);
        } catch (ParseException e) {
            logger.error("解析日期错误 ！ 格式： yyyy-MM-dd HH:mm:ss");
        }
        return date;
    }

    /**
     * 解析日期
     * @param source
     * @return
     */
    public static Date parseDate(String source) {
        Date date = null;
        try {
            date = dateFormat.parse(source);
        } catch (ParseException e) {
            logger.error("解析日期错误！ 格式： yyyy-MM-dd");
        }
        return date;
    }

    /**
     * 解析时间
     * @param source
     * @return
     */
    public static Date parseTime(String source) {
        Date date = null;
        try {
            date = timeFormat.parse(source);
        } catch (ParseException e) {
            logger.error("解析时间错误 ！ 格式： HH:mm:ss");
        }
        return date;
    }
}
