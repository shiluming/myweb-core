package com.myweb.framework.core.impl;

import com.myweb.framework.core.ClassScanner;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Created by rola
 */
public class DefaultClassScanner implements ClassScanner {


    @Override
    public List<Class<?>> getClassList(String packageName) {
        return null;
    }

    @Override
    public List<Class<?>> getClassListByAnnotation(String packageName, Class<? extends Annotation> annotation) {
        return null;
    }

    @Override
    public List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass) {
        return null;
    }
}
