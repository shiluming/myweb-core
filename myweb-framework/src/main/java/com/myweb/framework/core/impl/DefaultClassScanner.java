package com.myweb.framework.core.impl;

import com.myweb.framework.core.ClassScanner;
import com.myweb.framework.core.impl.support.AnnotationClassTemplate;
import com.myweb.framework.core.impl.support.ClassTemplate;
import com.myweb.framework.core.impl.support.SupperClassTemplate;

import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Created by rola
 */
public class DefaultClassScanner implements ClassScanner {


    @Override
    public List<Class<?>> getClassList(String packageName) {
        return new ClassTemplate(packageName){

            @Override
            public boolean checkAddClass(Class<?> cls) {
                String className = cls.getName();
                String pkgName = className.substring(0,className.lastIndexOf("."));
                return pkgName.startsWith(packageName);
            }
        }.getClassList();
    }

    @Override
    public List<Class<?>> getClassListByAnnotation(String packageName, final Class<? extends Annotation> annotationClass) {

        return new AnnotationClassTemplate(packageName,annotationClass) {

            @Override
            public boolean checkAddClass(Class<?> cls) {

                return cls.isAnnotationPresent(annotationClass);
            }
        }.getClassList();
    }

    @Override
    public List<Class<?>> getClassListBySuper(String packageName, Class<?> superClass) {
        return null;
    }
}
