package com.myweb.framework.utils;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by rola
 */
public class ObjectUtil {

    private static final Logger logger = LoggerFactory.getLogger(ObjectUtil.class);

    /**
     * 设置成员属性
     * @param obj
     * @param fieldName
     * @param fieldValue
     */
    public static void setField(Object obj,String fieldName,Object fieldValue) {

        try {
            if(PropertyUtils.isWriteable(obj, fieldName)) {
                PropertyUtils.setProperty(obj,fieldName,fieldValue);
            }
        }catch (Exception e) {
            logger.error("设置成员变量出错！",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取成员变量
     * @param obj
     * @param fieldName
     * @return
     */
    public static Object getFieldValue(Object obj,String fieldName) {
        Object propertyValue = null;
        try {
            if(PropertyUtils.isReadable(obj,fieldName)) {
                propertyValue = PropertyUtils.getProperty(obj,fieldName);
            }
        } catch (Exception e) {
            logger.error("获取成员变量出错！",e);
            throw new RuntimeException(e);
        }
        return propertyValue;
    }

    /**
     * 复制所有成员变量   -————》 看看这个方法做了哪些事情
     * @param source
     * @param target
     * @return
     */
    public static void copyFields(Object source, Object target) {
        try {
            for(Field field : source.getClass().getDeclaredFields()) {
                //若不为static变量，则进行复制
                if(!Modifier.isStatic(field.getModifiers())) {
                    field.setAccessible(true);// 可操作成员变量， 这个是什么意思？
                    field.set(target,field.get(source));
                }
            }
        }catch (Exception e) {
            logger.error("复制成员变量出错！",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过反射创建实例
     * @param className
     * @param <T>
     * @return
     */
    public static <T> T newInstance(String className) {
        T instance;
        try {
            Class<?> commandClass = ClassUtil.loadClass(className);
            instance = (T)commandClass.newInstance();
        } catch(Exception e) {
            logger.error("创建实例出错！",e);
            throw new RuntimeException(e);
        }
        return instance;
    }

    /**
     * 获取对象的字段映射（字段名 =》 字段值） 忽略 static 字段
     * @param obj
     * @return
     */
    public static Map<String,Object> getFieldMap(Object obj) {
        return getFieldMap(obj,true);
    }
    /**
     * 获取对象字段映射
     * @param obj
     * @param isStaticIgnored
     * @return
     */
    public static Map<String,Object> getFieldMap(Object obj,boolean isStaticIgnored) {
        Map<String,Object> fieldMap = new LinkedHashMap<String, Object>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field : fields) {
            if(isStaticIgnored && Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            String fieldName = field.getName();
            Object fieldValue = ObjectUtil.getFieldValue(obj,fieldName);
            fieldMap.put(fieldName,fieldValue);
        }
        return fieldMap;
    }
}