package com.myweb.framework.utils;

import org.apache.commons.collections4.MapUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by rola
 */
public class MapUtil {

    /**
     * 判断 Map 是否为空
     * @param map
     * @return
     */
    public static boolean isEmpty(Map<?,?> map) {
        return MapUtils.isEmpty(map);
    }

    /**
     * 判断 map 是否非空
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map<?,?> map) {
        return MapUtils.isNotEmpty(map);
    }

    /**
     * map key value 倒置
     * @param source
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K,V> Map<V,K> invert(Map<K,V> source) {
        Map<V,K> target = null;
        if(isNotEmpty(source)) {
            target = new LinkedHashMap<V, K>(source.size());
            for(Map.Entry<K,V> entry : source.entrySet()) {
                target.put(entry.getValue(),entry.getKey());
            }
        }
        return target;
    }

}
