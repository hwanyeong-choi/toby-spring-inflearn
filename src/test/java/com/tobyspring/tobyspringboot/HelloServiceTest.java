package com.tobyspring.tobyspringboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloServiceTest {

    /*
        HelloApiTest와 달리 HelloServiceTest는 네트워크 통신을
        이용하지 않고 테스트를 진행하기 때문에 수행속도가 월등히 빠릅니다.
     */

    @Test
    void simpleHelloService() {
        HelloService helloService = new SimpleHelloService();
        String result = helloService.sayHello("Test");
        Assertions.assertThat(result).isEqualTo("Hello Test");
    }

    @Test
    void helloDecorator() {
        HelloDecorator helloDecorator = new HelloDecorator(name -> name);
        String result = helloDecorator.sayHello("Test");
        Assertions.assertThat(result).isEqualTo("*Test*");
    }

}
