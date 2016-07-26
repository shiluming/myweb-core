package com.myweb.framework.utils;

import org.apache.commons.lang3.ArrayUtils;
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

    /**
     * 转为 String[] 数组
     * @param objArrays
     * @return
     */
    public static String[] castStringArray(Object[] objArrays) {
        if(objArrays == null) {
                objArrays = new Object[0];
        }
        String[] strArray = new String[objArrays.length];
        if(ArrayUtils.isNotEmpty(objArrays)) {
            for(int i=0;i<objArrays.length;i++) {
                strArray[i] = castString(objArrays[i]);
            }
        }
        return strArray;

    }

    /**
     * 转为 double 数组
     * @param objArrays
     * @return
     */
    public static double[] castDoubleArray(Object[] objArrays) {
        if(objArrays == null) {
            objArrays = new Object[0];
        }
        double[] doubleArray = new double[objArrays.length];
        if(ArrayUtils.isNotEmpty(objArrays)) {

            for(int i=0;i<objArrays.length;i++) {
                doubleArray[i] = castDouble(objArrays[i]);
            }
        }
        return doubleArray;
    }

    public static long[] castLongArray(Object[] objArrays) {
        if(objArrays == null) {
            objArrays = new Object[0];
        }
        long[] longArray = new long[objArrays.length];
        if(ArrayUtils.isNotEmpty(objArrays)) {

            for(int i=0;i<objArrays.length;i++) {
                longArray[i] = castLong(objArrays[i]);
            }
        }
        return longArray;

    }

    public static int[] castIntArray(Object[] objArray) {
        if(objArray == null) {
            objArray = new Object[0];
        }
        int[] intArray = new int[objArray.length];
        if(ArrayUtil.isNotEmpty(objArray)) {
            for(int i=0;i<objArray.length;i++) {
                intArray[i] = castInt(objArray[i]);
            }
        }
        return intArray;
    }

    public static boolean[] castBooleanArray(Object[] objArray) {
        if(objArray == null) {
            objArray = new Object[0];
        }
        boolean[] booleanArray = new boolean[objArray.length];
        if(ArrayUtil.isNotEmpty(objArray)) {
            for(int i=0;i<objArray.length;i++) {
                booleanArray[i] = castBoolean(objArray[i]);
            }
        }
        return booleanArray;
    }
}
