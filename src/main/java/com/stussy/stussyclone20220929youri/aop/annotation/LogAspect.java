package com.stussy.stussyclone20220929youri.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
//TYPE(클래스)위에 달 수 있다. METHOD(메소드) 위에 사용할 수 있다 선언
public @interface LogAspect {

}
