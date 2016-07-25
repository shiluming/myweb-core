package com.myweb.framework.utils;

import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by rola
 */
public class ArrayUtil {

    /**
     * 判断数组是否为非空
     */
    public static boolean isNotEmpty(Object[] array) {
        return ArrayUtils.isNotEmpty(array);
    }

    /**
     * 判断数组是否为空
     */
    public static boolean isEmpty(Object[] array) {
        return ArrayUtils.isEmpty(array);
    }

    /**
     * 连接数组
     * @param arrayFirst
     * @param arraySec
     * @return
     */
    public static Object[] concat(Object[] arrayFirst, Object[] arraySec) {
        return ArrayUtils.addAll(arrayFirst,arraySec);
    }

    /**
     * 判断对象是否在数组中
     * @param array
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> boolean contains(T[] array, T obj) {
        return ArrayUtils.contains(array,obj);
    }

}
