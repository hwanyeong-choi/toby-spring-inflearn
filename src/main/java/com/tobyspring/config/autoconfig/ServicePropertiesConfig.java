package com.tobyspring.config.autoconfig;

import com.tobyspring.config.MyAutoConfiguration;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

@MyAutoConfiguration
public class ServicePropertiesConfig {

    @Bean
    ServiceProperties serviceProperties(Environment environment) {
        return Binder.get(environment).bind("", ServiceProperties.class).get();
    }

}
