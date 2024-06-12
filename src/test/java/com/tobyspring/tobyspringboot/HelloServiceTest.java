package com.tobyspring.tobyspringboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@UnitTest
@interface FastUnitTest { }


/*
    메타 애노테이션을 통해서 단위테스트라는 점을 애노테이션 명으로
    직관적인 설명을 위해 만들 수도 있습니다.

    @UnitTest를 애노테이션을 메타 애노테이션으로 활용하려면
    @Target 구성정보에 ElementType.ANNOTATION_TYPE을 추가하여
    메타 애노테이션으로 활용할 수 있게 할 수 있습니다.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Test
@interface UnitTest { }

public class HelloServiceTest {

    /*
        HelloApiTest와 달리 HelloServiceTest는 네트워크 통신을
        이용하지 않고 테스트를 진행하기 때문에 수행속도가 월등히 빠릅니다.
     */

    /*
        JUnit Test 애노테이션을 메타로 가지고 있고 단위테스트라는 점을 애노테이션 명으로
        표현하기위해 @UnitTest를 만들었습니다. 메타 애노테이션으로 @Test를 가지고 있기때문에
        JUnit의 테스트 기능을 그대로 사용할 수 있습니다.
    */
    @UnitTest
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
