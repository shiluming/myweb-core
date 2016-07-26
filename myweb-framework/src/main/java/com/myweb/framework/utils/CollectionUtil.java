package com.myweb.framework.utils;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Collection;

/**
 * Created by rola
 */
public class CollectionUtil {

    /**
     * 判断集合是否为非空
     * @param collection
     * @return
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return CollectionUtils.isNotEmpty(collection);
    }

    /**
     * 判断集合是否为空
     * @param collection
     * @return
     */
    public static boolean isEmpty(Collection<?> collection) {
        return CollectionUtils.isEmpty(collection);
    }
}
