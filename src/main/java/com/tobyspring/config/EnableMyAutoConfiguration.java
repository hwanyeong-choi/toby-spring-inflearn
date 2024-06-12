package com.tobyspring.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
    구성정보를 등록할 DeferredImportSelector 구현체 클래스인
    MyAutoConfigImportSelector.class 만 @Import 하게된다면
    동적으로 구성정보를 등록할 수 있게 됩니다.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MyAutoConfigImportSelector.class)
public @interface EnableMyAutoConfiguration { }
