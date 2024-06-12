package com.tobyspring.tobyspringboot;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/*
	Bean을 Spring Container가 인지하기 위해서는 한가지 작업을 더 해야하는데
	그게 뭐냐면 SpringContainer가 Bean오브젝트를 생성하는 Factory Method를 가진 클래스다
	라는것을 인지하도록 클래스 레벨에도 애노테이션을 붙여줘야 합니다.

	그 어노테이션은 Configuration입니다.
 */

/*
	@ComponentScan 애노테이션은 Component 애노테이션이 붙은 클래스들을 찾아서 빈으로 등록하라는 명령을
	@ComponentScan을 붙이는걸로 컨테이너에게 전달이 됩니다.

	@ComponentScan이 붙어있는 클래스가 있는 패키지부터 시작해 그 하위 패키지를 확인해서
	컴포넌트라는 애노테이션이 붙은 모든 클래스를 Bean으로 등록합니다 Bean으로 등록할 때
	필요하다면 의존 오브젝트를 찾아내고 이걸 생성자를 호출할 때 파라미터로 넘겨줍니다.
 */

/*
	기존에 존재하였는 @ComponentScan, @Configuration 애노테이션을
	메타 애노테이션으로 가지고 있는 합성 @MySpringBootAnnotation을 구성하여
	적용합니다.
 */
@MySpringBootAnnotation
public class TobyspringbootApplication {

//	public static void main(String[] args) {
//		MySpringApplication.run(TobyspringbootApplication.class, args);
//	}

	// SpringBoot 프로젝트 생성시 프로젝트를 생성하는 메인 코드를 보자
	// 위 우리가 직접 구현한 MySpringApplication.run(TobyspringbootApplication.class, args); 유사합니다.
	public static void main(String[] args) {
		SpringApplication.run(TobyspringbootApplication.class, args);
	}

}
