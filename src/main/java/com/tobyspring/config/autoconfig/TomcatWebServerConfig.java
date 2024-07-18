package com.tobyspring.config.autoconfig;

import com.tobyspring.config.ConditionalMyOnClass;
import com.tobyspring.config.MyAutoConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

@MyAutoConfiguration
@ConditionalMyOnClass("org.apache.catalina.startup.Tomcat")
@EnableMyConfigurationProperties(ServiceProperties.class)
public class TomcatWebServerConfig {

    @Value("${contextPath:/}")
    String contextPath;

    @Bean("tomcatWebServerFactory")

    /*
        자동 구성정보를 등록할때는 deferred import selector 구현체를 사용해야하는데
        그러한 이유는 사용자 구성정보를 통해 먼저 Bean을 등록하고 그후 자동 구성 정보의 Bean을 등록하기 위함입니다.
        따라서 이미 사용자 구성정보에 이미 등록되어있는 Bean이라면 자동 구성 정보에서는 제외하는 애노테이션이 @ConditionalOnMissingBean
        입니다.
    */
    @ConditionalOnMissingBean
    public ServletWebServerFactory servletWebServerFactory(ServiceProperties serviceProperties) {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.setContextPath(serviceProperties.getContextPath());
        factory.setPort(serviceProperties.getPort());
        return factory;
    }

}
