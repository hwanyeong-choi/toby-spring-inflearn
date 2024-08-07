package com.tobyspring.tobyspringboot.helloboot;

import com.tobyspring.tobyspringboot.TobyspringbootApplication;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TobyspringbootApplication.class)
@TestPropertySource("classpath:/application.properties")
@Transactional
public @interface HellobootTest {
}
