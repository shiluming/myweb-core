package com.myweb.framework.core;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Created by rola
 */
public interface ClassScanner {

    /**
     * 获取指定包名中的所有类
     */
    List<Class<?>> getClassList(String packageName);

    /**
     * 获取指定包名中指定注解的类
     * @param packageName
     * @param annotation
     * @return
     */
    List<Class<?>> getClassListByAnnotation(String packageName,Class<? extends Annotation> annotation);

    /**
     * 获取指定包中指定父类或接口的类
     * @param packageName
     * @param superClass
     * @return
     */
    List<Class<?>> getClassListBySuper(String packageName,Class<?> superClass);
}
