package com.tobyspring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
    @Retention 의 디폴트값은 RetentionPolicy.CLASS 입니다.
    RetentionPolicy.CLASS로 구성된다면 애노테이션 정보가 컴파일된 class파일 까지는 존재하지만
    runtime 메모리로 로딩할 때는 해당 애노테이션 정보가 사라지게 됩니다.

    따라서 우리는 해당 애노테이션 정보가 runtime 에도 메모리에 애노테이션 정보가
    유지되어야 하므로 @Retention(RetentionPolicy.RUNTIME)으로 설정합니다.
 */

/*
    @Target 애노테이션에 ElementType.TYPE을 적용한다면 class, interface, enum
    세가지 종ㄹ의 대상에게 뷰여할 수 있는 애노테이션을 구성할 수 있습니다.
 */

/*
    @Import를 통해 다른 패키지에있는 구성정보를 불러와 등록할 수 있습니다.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Configuration
@ComponentScan
@EnableMyAutoConfiguration
public @interface MySpringBootApplication { }
