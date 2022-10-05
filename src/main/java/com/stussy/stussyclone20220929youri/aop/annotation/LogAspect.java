package com.stussy.stussyclone20220929youri.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD}) //TYPE(클래스)와 METHOD(메소드) 에 사용한다 선언
public @interface LogAspect {

}
