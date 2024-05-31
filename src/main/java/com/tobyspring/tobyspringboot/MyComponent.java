package com.tobyspring.tobyspringboot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

// Retention 이 어노테이션으 언제까지 유지될것인가 하는 정책을 결정하는 어노테이션
@Retention(RetentionPolicy.RUNTIME)
// Target 애노테이션을 적용할 대상을 지정해 줄 수 있습니다. 지정된 Target 위치에만 적용할 수 있도록 합니다.
@Target(ElementType.TYPE)
// 메타 애노테이션 @Component
@Component
public @interface MyComponent { }
