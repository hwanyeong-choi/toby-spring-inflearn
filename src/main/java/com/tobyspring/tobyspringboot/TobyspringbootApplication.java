package com.tobyspring.tobyspringboot;


import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
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
@ComponentScan
@Configuration
public class TobyspringbootApplication {

	@Bean
	public ServletWebServerFactory servletWebServerFactory() {
		return new TomcatServletWebServerFactory();
	}

	@Bean
	public DispatcherServlet dispatcherServlet() {
		return new DispatcherServlet();
	}

	public static void main(String[] args) {

		/*
			Spring Container 구현, DispatcherServlet을 사용하기 위해서는
			GenericApplicationContext이 아닌 GenericWebApplicationContext 형식으로 생성해야합니다.

			앞서 Bean을 우리가 직접 클래스 타입을 구성정보로 등록했다면 섹션 4-8에서는 구성정보를
			어노테이션으로 등록을 합니다. 다만 애노테이션으로 구성정보를 등록할때는 GenericWebApplicationContext
			은 애노테이션 구성정보를 인지할 수 없습니다. 다라서 변경을 해야하는데

			AnnotationConfigWebApplicationContext를 사용하면 애노테이션 구성정보를 Spring Container가 인지할 수 있습니다.
		 */
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext() {
			@Override
			protected void onRefresh() {
				super.onRefresh();
				/*
					Spring Boot에서 Tomcat Sublet 컨테이너를 내장해서 프로그앰에서 코드로
					쉽게 사용할 수 있도록 제공하는 클래스 TomcatServletWebServerFactory
				 */

				// Bean으로 등록한 ServletWebServerFactory를 요청하여 오브젝트를 반환받는다.
				ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
				// Bean으로 등록한 DispatcherServlet를 요청하여 오브젝트를 반환받는다.
				DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
				// Spring Container를 DispatcherServlet에 등록
				/*
					그러나 구지 등록하지 않아도 Spring Container가 ApplicationContext를 등록해줍니다.
					DispatcherServlet는 ApplicationContextAward 인터페이스를 구현하고있기 때문에
					Spring Continer는 ApplicationContextAward인터페이스를 구현하고 있는 오브젝트라면
					해당 오브젝트에 ApplicationContext를 setter 메소드를 통해 주입해줍니다.
				 */


				dispatcherServlet.setApplicationContext(this);

		/*
			웹서버 서블릿 컨테이너를 생성하는 함수 serverFactory.getWebServer()
			리턴타입이 디폴트로 설정한 Tomcat이라는 명칭은 사라지고  WebServer명칭으로 된이유는
			스프링 부트가 톰캣 외에 제티나 언더토우같은 다양한 서블릿 컨테이너를 지원할 수 있고
			지원하되 일관된 방식으로 사용할 수 있도록 동작하게할 수 있도록 추상화 해놨기 때문이다.
		 */

				WebServer webServer = serverFactory.getWebServer(servletContext -> {
					// DispatcherServlet등록
					servletContext.addServlet("dispatcherServlet", dispatcherServlet)
						.addMapping("/*");
				});

		/*
			Servlet 컨테이너 동작 함수
		 */
				webServer.start();

			}
		};

		// 애토테이션으로 bean구성정보를 등록한다면 bean구성정보를 담고있다고 명시하는 클래스레벨에 명시한 애노테이션
		// Configuration이 등록된 클래스를 구성정보로 Spring Container에 등록을 해줘야 합니다.
		applicationContext.register(TobyspringbootApplication.class);

		// ApplicationContext가 refresh메소드를 통해 빈 오브젝트를 생성합니다.
		applicationContext.refresh();

	}

}
