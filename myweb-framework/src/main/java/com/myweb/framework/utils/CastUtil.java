package com.myweb.framework.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by rola
 */
public class CastUtil {

    /**
     * 转为String 类型
     * @param obj
     * @return
     */
    public static String castString(Object obj) {
        return castString(obj,"");
    }

    /**
     * 转为 string ，提供默认值
     * @param obj
     * @param defaultValue
     * @return
     */
    public static String castString(Object obj, String defaultValue) {
        return obj != null? String.valueOf(obj) : defaultValue;
    }

    /**
     * 转为 double ， 提供默认值
     * @param obj
     * @param defaultValue
     * @return
     */
    public static double castDouble(Object obj,double defaultValue) {
        double doubleValue = defaultValue;
        if(obj != null) {
            String strValue = castString(obj);
            if(StringUtils.isNotEmpty(strValue)) {
                try {
                    doubleValue = Double.parseDouble(strValue);
                } catch (NumberFormatException e) {
                    doubleValue = defaultValue;
                }
            }
        }
        return doubleValue;
    }

    /**
     * 转为 double
     * @param obj
     * @return
     */
    public static double castDouble(Object obj) {
        return castDouble(obj,0);
    }

    /**
     * 转为 Int， 提供默认值
     * @param obj
     * @param defaultValue
     * @return
     */
    public static int castInt(Object obj,int defaultValue) {
        int intValue = defaultValue;
        if(obj != null) {
            String strValue = castString(obj);
            if(StringUtils.isNotEmpty(strValue)) {
                try {
                    intValue = Integer.parseInt(strValue);
                } catch (NumberFormatException e) {
                    intValue = defaultValue;
                }
            }
        }
        return intValue;
    }

    /**
     * 转为 Int
     * @param obj
     * @return
     */
    public static int castInt(Object obj) {
        return castInt(obj,0);
    }

    /**
     * 转为 long
     * @param obj
     * @return
     */
    public static long castLong(Object obj) {
        return castLong(obj, 0);
    }

    /**
     * 转为long，提供默认值
     * @param obj
     * @param defaultValue
     * @return
     */
    public static long castLong(Object obj,long defaultValue) {
        long longValue = defaultValue;
        if(obj != null) {
            String strValue = castString(obj);
            if(StringUtils.isNotEmpty(strValue)) {
                try {
                    longValue = Long.parseLong(strValue);
                } catch (NumberFormatException e) {
                    longValue = defaultValue;
                }
            }
        }
        return longValue;
    }

    /**
     * 转为 boolean ，提供默认值
     * @param obj
     * @param defaultValue
     * @return
     */
    public static boolean castBoolean(Object obj,boolean defaultValue) {
        boolean booleanValue = defaultValue;
        if(obj != null) {
            String strValue = castString(obj);
            if(StringUtils.isNotEmpty(strValue)) {
                try {
                    booleanValue = Boolean.parseBoolean(strValue);
                }catch (NumberFormatException e) {
                    booleanValue = defaultValue;
                }
            }
        }
        return booleanValue;
    }

    /**
     * 转为 boolean
     * @param obj
     * @return
     */
    public static boolean castBoolean(Object obj) {
        return castBoolean(obj,false);
    }

}
