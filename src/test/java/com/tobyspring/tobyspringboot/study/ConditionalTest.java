package com.tobyspring.tobyspringboot.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import org.springframework.context.annotation.*;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class ConditionalTest {

    @Test
    void conditional() {

        // true

        // Bean 구성정보를 확인하여 테스트할 수 있는객체 ApplicationContextRunner 생성
        ApplicationContextRunner contextRunner1 = new ApplicationContextRunner();

        // Configuration 구성정보 Class를 매개변수로 넘겨주고
        contextRunner1.withUserConfiguration(Config1.class)
                .run(context -> {
                    // context에 해당 빈들이 존재하는지 테스트한다.
                    assertThat(context).hasSingleBean(MyBean.class);
                    assertThat(context).hasSingleBean(Config1.class);
                });


        // false
        ApplicationContextRunner contextRunner2 = new ApplicationContextRunner();
        contextRunner2.withUserConfiguration(Config2.class)
                .run(context -> {

                    // doesNotHaveBean의 경우에는 존재하지않을경우를 테스트한다.
                    assertThat(context).doesNotHaveBean(MyBean.class);
                    assertThat(context).doesNotHaveBean(Config2.class);
                });

    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @Conditional(BooleanCondition.class)
    @interface BooleanConditional {
        boolean value();
    }

    @Configuration
    @BooleanConditional(true)
    static class Config1 {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }
    }

    @Configuration
    @BooleanConditional(false)
    static class Config2 {
        @Bean
        MyBean myBean() {
            return new MyBean();
        }
    }

    static class BooleanCondition implements Condition {

        @Override
        public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
            // BooleanCondition 애노테이션에 매개변수로 넘겨지는 메타정보를 확인한다.
            // BooleanConditional의 애트리뷰트 정보를 Map형태로 가져온다.
            Map<String ,Object> annotationAttributes = metadata.getAnnotationAttributes(BooleanConditional.class.getName());
            // value이름으로된 애트리뷰트를 가져온다.
            Boolean value = (Boolean) annotationAttributes.get("value");
            return value;
        }
    }

    static class MyBean { }

}
