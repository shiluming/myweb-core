package com.shilm.commons;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * Created by rola
 */
public class PropsUtils {

    private static final Logger logger = LoggerFactory.getLogger(PropsUtils.class);

    public static Properties loadPropos(String fileName) {
        Properties props = null;
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
            if(is==null) {
                throw new FileNotFoundException(fileName + " File is not found");
            }
            props = new Properties();
            props.load(is);
        } catch (IOException e) {
            logger.error("load properties file failure",e);
        } finally {
            if(is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                   logger.error("close input stream failure",e);
                }
            }
        }
        return props;
    }

    public static String getString(Properties props,String key) {
        return getString(props,key,"");
    }

    public static String getString(Properties props,String key, String defaultValue) {
        String value = defaultValue;
        if(props.containsKey(key)) {
            value = props.getProperty(key);
        }
        return value;
    }
    public static int getInt(Properties props,String key) {
        return getInt(props,key,0);
    }
    public static int getInt(Properties props, String key,int defaultValue) {
        int value = defaultValue;
        if(props.containsKey(key)) {
            value = CastUtils.castInt(props.getProperty(key));
        }
        return value;
    }

    public static boolean getBoolean(Properties props, String key) {
        return getBoolean(props,key,true);
    }

    public static boolean getBoolean(Properties props,String key,boolean defaultValue) {
        boolean value = defaultValue;
        if(props.containsKey(key)) {
            value = CastUtils.castBoolean(props.getProperty(key));
        }
        return value;
    }

}
