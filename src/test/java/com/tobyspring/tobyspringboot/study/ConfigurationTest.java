package com.tobyspring.tobyspringboot.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConfigurationTest {

    @Test
    void configuration() {

        /*
            일반적으로 팩토리 메소드로 객체를 생성한다면
            팩토리 메소드를 실행할때마다 새로운 객체가 생성이됩니다.
            MyConfig에 Bean1, Bean2로 비교해보도록 하겠습니다.
         */

        MyConfig myConfig = new MyConfig();

        Bean1 bean1 = myConfig.bean1();
        Bean2 bean2 = myConfig.bean2();

        /*
            당연히 Bean1, Bean2를 팩토리 메소드로 생성한후
            bean1, bean2가 모두 가지고 있는 common의 객체주소가
            다르기 때문에 같이 않다는 오류가 발생합니다.
        */
        // Assertions.assertThat(bean1.common).isSameAs(bean2.common);

        /*
            그러나 Spring Container를 이용해서 구성정보를 등록하면
            팩토리 메소드로 구현하여도 Bean으로 등록한 팩토리 메소드로
            생성한 객체는 싱글턴으로 유지되게 됩니다.
         */

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext();
        ac.register(MyConfig.class);
        ac.refresh();

        Bean1 containerBean1 = ac.getBean(Bean1.class);
        Bean2 containerBena2 = ac.getBean(Bean2.class);
        Assertions.assertThat(containerBean1.common).isSameAs(containerBena2.common);


    }

    @Configuration
    static class MyConfig {
        @Bean
        Common common() {
            return new Common();
        }

        @Bean
        Bean1 bean1() {
            return new Bean1(common());
        }

        @Bean
        Bean2 bean2() {
            return new Bean2(common());
        }

    }

    static class Bean1 {
        private final Common common;

        public Bean1(Common common) {
            this.common = common;
        }
    }

    static class Bean2 {
        private final Common common;

        public Bean2(Common common) {
            this.common = common;
        }
    }

    static class Common {

    }

    // Bean1 <-- Common
    // Bean2 <-- Common

}
