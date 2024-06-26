package com.tobyspring.config;

import org.springframework.context.annotation.Conditional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 커스텀 메타 Conditional 구현
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Conditional(MyOnClassCondition.class)
public @interface ConditionalMyOnClass {
    // 메타정보를 통해 매개변수를 넘겨줄 수 있도록 한다.
    String value();
}
