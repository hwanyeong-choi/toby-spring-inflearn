package com.tobyspring.tobyspringboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class HelloControllerTest {

    /*
        직접 Spring을 의존하지 않고 자바 객체 생성만으로 객체를 테스트 한다면 장점이 있는데
        바로 고립된 테스트가 가능하다는 점 입니다.

        HelloController가 의존하고있는 HelloService로부터 고립시켜 테스트가 가능합니다.
        HelloController 객체가 생성될때 익명으로 HelloService구현체를 직접 주입하여
        HelloService에서 발생할 수 있는 오류를 제외하고 온전히 HelloController만 테스트할
        수 있게 됩니다.

        HelloController가 의존하는 HelloService를 직접 구현하여 주입하는 객체를
        Test Stub이라고도 부릅니다.

        테스트를 위한 Test Stub를 주입하는 부분을 수동 DI라고 부르기도 합니다.
     */

    @Test
    void helloController() {
        HelloController helloController = new HelloController(name -> name);
        String result = helloController.hello("Test");
        Assertions.assertThat(result).isEqualTo("Test");
    }

    /*
        HelloController에서 name 파라미터가 null 또는 공백 문자일경우 IllegalArgumentException 오류가 발생합니다.
        null, 공백 문자열이 전달되었는데도 불가하고 IllegalArgumentException이 발생하지 않을경우 테스트가 실패합니다.
     */
    @Test
    void failHelloController() {
        HelloController helloController = new HelloController(name -> name);
        Assertions.assertThatThrownBy(() -> helloController.hello(null)).isInstanceOf(IllegalArgumentException.class);
        Assertions.assertThatThrownBy(() -> helloController.hello("")).isInstanceOf(IllegalArgumentException.class);
    }

}
