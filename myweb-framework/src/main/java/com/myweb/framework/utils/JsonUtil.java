package com.myweb.framework.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by rola
 */
public class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * 将 java 对象转换为 json 字符串
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String toJson(T obj) {
        String jsonStr;
        try {
            jsonStr = objectMapper.writeValueAsString(obj);
        } catch(Exception e) {
            logger.error(" Java 对象转 Json 字符串出错！",e);
            throw new RuntimeException(e);
        }
        return jsonStr;
    }

    /**
     * 将json 装换为 java 对象
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json,Class<T> type) {
        T obj;
        try {
            obj = objectMapper.readValue(json, type);

        } catch (IOException e) {
            logger.error("Json 转 Java 对象出错", e);
            throw new RuntimeException(e);
        }
        return obj;
    }
}
