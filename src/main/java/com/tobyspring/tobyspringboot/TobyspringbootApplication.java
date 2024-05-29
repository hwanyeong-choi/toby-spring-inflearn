package com.tobyspring.tobyspringboot;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;

public class TobyspringbootApplication {

	public static void main(String[] args) {
		/*
			Spring Boot에서 Tomcat Sublet 컨테이너를 내장해서 프로그앰에서 코드로
			쉽게 사용할 수 있도록 제공하는 클래스 TomcatServletWebServerFactory
		 */
		TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

		/*
			웹서버 서블릿 컨테이너를 생성하는 함수
			리턴타입이 디폴트로 설정한 Tomcat이라는 명칭은 사라지고  WebServer명칭으로 된이유는
			스프링 부트가 톰캣 외에 제티나 언더토우같은 다양한 서블릿 컨테이너를 지원할 수 있고
			지원하되 일관된 방식으로 사용할 수 있도록 동작하게할 수 있도록 추상화 해놨기 때문이다.
		 */
		WebServer webServer = serverFactory.getWebServer();

		/*
			Servlet 컨테이너 동작 함수
		 */
		webServer.start();
	}

}
