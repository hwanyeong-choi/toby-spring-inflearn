package com.tobyspring.tobyspringboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/*
    ServletWebServerFactory, DispatcherServlet Bean구성정보를 TobyspringbootApplication
    클래스에서 분리하여 별도 Config 클래스에 구성합니다.

    해당 Config, ServletWebServerFactory, DispatcherServlet class가
    Spring Container에서 관리될 수 있도록 @Confuguration, @Bean애노테이션을
    활용하여 Spring Container에 등록해줍니다.
 */

@Configuration
public class Config {

    @Bean
    public ServletWebServerFactory servletWebServerFactory() {
        return new TomcatServletWebServerFactory();
    }

    @Bean
    public DispatcherServlet dispatcherServlet() {
        return new DispatcherServlet();
    }

}
