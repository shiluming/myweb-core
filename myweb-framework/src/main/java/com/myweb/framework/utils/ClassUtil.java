package com.myweb.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by rola
 */
public class ClassUtil {

    private static final Logger logger = LoggerFactory.getLogger(ClassUtil.class);

    /**
     * get ClassLoader
     */
    public static ClassLoader getClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * load class
     */
    public static Class<?> loadClass(String className,boolean isInitialized) {
        Class<?> cls;
        try {
            cls = Class.forName(className,isInitialized,getClassLoader());

        } catch (ClassNotFoundException e) {
            logger.error("load class failure ", e);
            throw new RuntimeException(e);
        }
        return cls;
    }

    /**
     * load class from package
     */
    @Deprecated
    public static Set<Class<?>> getClassSet(String packageName) {
        Set<Class<?>> classSet = new HashSet<Class<?>>();
        return classSet;
    }

    /**
     * get class path
     */
    public static String getClassPath() {
        String classpath = "";
        URL resource =  getClassLoader().getResource("");
        if(resource != null) {
            classpath = resource.getPath();
        }
        return classpath;
    }

    /**
     * load class
     * @param name
     * @return
     */
    public static Class<?> loadClass(String className) {
        return loadClass(className,true);
    }

    /**
     * is Int class
     * @param type
     * @return
     */
    public static boolean isInt(Class<?> type) {
        return type.equals(int.class) || type.equals(Integer.class);
    }

    /**
     * is long class
     * @param type
     * @return
     */
    public static boolean isLong(Class<?> type) {
        return type.equals(long.class) || type.equals(Long.class);
    }

    /**
     * is double
     * @param type
     * @return
     */
    public static boolean isDouble(Class<?> type) {
        return type.equals(double.class) || type.equals(Double.class);
    }

    /**
     * is string class
     * @param type
     * @return
     */
    public static boolean isString(Class<?> type) {
        return type.equals(String.class);
    }


    public static void main(String[] args) {
        String str = "22";
    }
}
