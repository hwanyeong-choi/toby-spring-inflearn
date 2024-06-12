package com.tobyspring.config;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/*
    DeferredImportSelector을 구현하여 config class의
    이름을 selectImports 메소드를 통해 Sring배열로 리턴합니다.
 */
public class MyAutoConfigImportSelector implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {
                "com.tobyspring.config.autoconfig.DispatcherServletConfig",
                "com.tobyspring.config.autoconfig.TomcatWebserverConfig"
        };
    }
}
