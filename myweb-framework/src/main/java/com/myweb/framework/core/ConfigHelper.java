package com.myweb.framework.core;

import com.myweb.framework.FrameworkConstant;
import com.myweb.framework.utils.PropsUtils;

import java.util.Map;
import java.util.Properties;

/**
 * Created by rola
 */
public class ConfigHelper {

    /**
     * 属性文件对象
     */
    private static final Properties configProps = PropsUtils.loadPropos(FrameworkConstant.CONFIG_PROPS);

    /**
     * 获取 String
     * @param key
     * @return
     */
    public static String getString(String key) {
        return PropsUtils.getString(configProps,key);
    }

    public static String getString(String key,String defaultValue) {
        return PropsUtils.getString(configProps,key,defaultValue);
    }

    public static int getInt(String key) {
        return PropsUtils.getInt(configProps,key);
    }

    public static int getInt(String key,int defaultValue) {
        return PropsUtils.getInt(configProps,key,defaultValue);
    }

    public static boolean getBoolean(String key) {
        return PropsUtils.getBoolean(configProps,key);
    }

    public static boolean getBoolean(String key,boolean defaultValue) {
        return PropsUtils.getBoolean(configProps,key,defaultValue);
    }


}
