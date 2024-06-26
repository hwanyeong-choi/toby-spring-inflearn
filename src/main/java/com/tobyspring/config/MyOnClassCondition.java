package com.tobyspring.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.util.ClassUtils;

import java.util.Map;

public class MyOnClassCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> attrs = metadata.getAnnotationAttributes(ConditionalMyOnClass.class.getName());
        // 메타정보로 클래스의 경로를 입력받아 해당 클래스가 존재하는지 여부를 체크한다.
        String value = (String) attrs.get("value");
        return ClassUtils.isPresent(value, context.getClassLoader());
    }
}
