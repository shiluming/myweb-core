package com.shilm.commons;

import org.apache.commons.lang3.*;

/**
 * Created by rola
 */
public class CastUtils {

    public static String castString(Object obj) {
        return CastUtils.castString(obj,"");
    }

    public static String castString(Object obj,String defaultValue) {
        return obj != null ? String.valueOf(obj) : defaultValue;
    }

    public static double castDouble(Object obj) {
        return CastUtils.castDouble(obj,0);
    }

    public static double castDouble(Object obj,double defalueValue) {
        double value = defalueValue;
        if(obj!=null) {
            String strValue=castString(obj);
            if(StringUtils.isNotEmpty(strValue)) {
                try {
                    value = Double.parseDouble(strValue);
                } catch (NumberFormatException e) {
                    value = defalueValue;
                }
            }
        }
        return value;
    }

    public static long castLong(Object obj) {
        return CastUtils.castLong(obj,0);
    }

    public static long castLong(Object obj,long defaultValue) {
        long value = defaultValue;
        if( obj != null) {
            String strValue = castString(obj);
            if(StringUtils.isNotEmpty(strValue)) {
                try {
                    value = Long.parseLong(strValue);
                }catch (NumberFormatException e) {
                    value = defaultValue;
                }
            }
        }
        return value;
    }

    public static int castInt(Object obj,int defaultValue) {
        int value = defaultValue;
        if(obj!=null) {
            String strValue = castString(obj);
            if(StringUtils.isNotEmpty(strValue)) {
                try {
                    value = Integer.parseInt(strValue);
                } catch (NumberFormatException e) {
                    value = defaultValue;
                }
            }
        }
        return value;
    }

    public static int castInt(Object obj) {
        return castInt(obj,0);
    }

    public static boolean castBoolean(Object obj) {
        return castBoolean(obj,true);
    }

    public static boolean castBoolean(Object obj,boolean defaultValue) {
        boolean value = true;
        if(obj!=null) {
            value = Boolean.parseBoolean(castString(obj));
        }
        return value;
    }

}
