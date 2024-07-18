package com.tobyspring.tobyspringboot.helloboot;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.*;

public class HelloApiTest {

    // JUNIT 테스트 코드
    @Test
    void helloApi() {
        // http localhost:8080/hello?name=Spring
        /* 테스트 목적일때는 RestTemplate 보다는 TestRestTemplate를 사용하는게 좋다.
           그 이유는 RestTemplate는 오류가 발생할경우는 예외가 발생하는데 반해 TestRestTemplate는
           오류가 발생한 경우에도 예외를 발생시키지않고 해당 응답 결과를 반환해 줍니다. 따르서 검증하기
           용이합니다.
        */
        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> response
                = rest.getForEntity("http://localhost:8080/app/hello?name={name}", String.class, "Spring");

        /*
            응답 검증
            State code: 200
            header (content-type): text/plain
            body: Hello Spring
         */
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        assertThat(response.getBody()).isEqualTo("*Hello Spring*");

    }

    /*
        네트워크 통신을 통한 테스트를 진행할때도 오류에대한 테스트가 가능합니다.
     */

    @Test
    void failHelloApi() {

        TestRestTemplate rest = new TestRestTemplate();

        ResponseEntity<String> response
                = rest.getForEntity("http://localhost:9090/app/hello?name", String.class);

        /*
            응답 검증
            State code: 500
         */
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
