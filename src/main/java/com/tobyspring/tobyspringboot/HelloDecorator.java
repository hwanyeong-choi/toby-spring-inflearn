package com.tobyspring.tobyspringboot;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
/*
    @Primary 어노테이션을 사용한다면 복수의 HelloService 구현체가 있을때
    Spring Container가 의존성 주입을 할때 복수의 구현체중 HelloDecorator을 우선적으로
    주입을 하게 됩니다.
 */

@Service
@Primary
public class HelloDecorator implements HelloService {

    private final HelloService helloService;

    /*
        Hellodecorator에 주입되는 HelloService는 자기 자신인 HelloService구현체를
        제외하고 단하나만 남은 SimpleHelloService를 Spring Container가 주입하게 됩니다.
        단 SimpleHelloService외 추가적으로 HelloService의 구현체가 추가적으로 존재한다면
        Spring Container는 어떤 구현체를 주입할지 결정할 수 없게 되므로 오류가 발생하게 됩니다.

     */
    public HelloDecorator(HelloService helloService) {
        this.helloService = helloService;
    }

    @Override
    public String sayHello(String name) {
        return "*" + helloService.sayHello(name) + "*";
    }
}
