package com.tobyspring.tobyspringboot;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import jdk.jfr.ContentType;

public class TobyspringbootApplication {

	public static void main(String[] args) {
		/*
			Spring Boot에서 Tomcat Sublet 컨테이너를 내장해서 프로그앰에서 코드로
			쉽게 사용할 수 있도록 제공하는 클래스 TomcatServletWebServerFactory
		 */
		TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();

		/*
			웹서버 서블릿 컨테이너를 생성하는 함수 serverFactory.getWebServer()
			리턴타입이 디폴트로 설정한 Tomcat이라는 명칭은 사라지고  WebServer명칭으로 된이유는
			스프링 부트가 톰캣 외에 제티나 언더토우같은 다양한 서블릿 컨테이너를 지원할 수 있고
			지원하되 일관된 방식으로 사용할 수 있도록 동작하게할 수 있도록 추상화 해놨기 때문이다.
		 */

		WebServer webServer = serverFactory.getWebServer(new ServletContextInitializer() {

			/*
				serverFactory를 통해서 서블릿 컨테이너가 생성되었다면 서블릿 컨테이너에
				서블릿을 등록한다. 서블릿을 등록하는건 webserver생성시 ServletContextInitializer을
				구현하는 객체를 매개변수로 전달하면 된다.
			 */
			@Override
			public void onStartup(ServletContext servletContext) throws ServletException {
				servletContext.addServlet("hello", new HttpServlet() {
					@Override
					protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
						// 파라미터를 전달받는데 name으로 선언된 파라미터를 추출한다.
						String name = req.getParameter("name");

						// 응답코드 설정
						resp.setStatus(HttpStatus.OK.value());
						// 헤더에 Content Type 명시
						resp.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE);
						// Content Type과 일치하는 응답값 바디를 입력
						resp.getWriter().println("Hello " + name);
					}
					// /hello path 매핑
				}).addMapping("/hello");
			}
		});

		/*
			Servlet 컨테이너 동작 함수
		 */
		webServer.start();
	}

}
