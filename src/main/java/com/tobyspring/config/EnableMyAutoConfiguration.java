package com.tobyspring.config;

import com.tobyspring.config.autoconfig.DispatcherServletConfig;
import com.tobyspring.config.autoconfig.TomcatWebserverConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import({DispatcherServletConfig.class, TomcatWebserverConfig.class})
public @interface EnableMyAutoConfiguration { }
