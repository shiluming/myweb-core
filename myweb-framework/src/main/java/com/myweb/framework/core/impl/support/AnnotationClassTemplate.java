package com.myweb.framework.core.impl.support;

import java.lang.annotation.Annotation;

/**
 * Created by rola
 */
public abstract class AnnotationClassTemplate extends ClassTemplate{

    private final Class<? extends Annotation> annotation;

    protected AnnotationClassTemplate(String packageName , Class<? extends Annotation> annotationClass) {
        super(packageName);
        this.annotation = annotationClass;
    }
}
