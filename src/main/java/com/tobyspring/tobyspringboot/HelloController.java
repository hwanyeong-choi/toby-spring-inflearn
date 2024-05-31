package com.tobyspring.tobyspringboot;

import java.util.Objects;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
	DispatchServlet이 맵핑정보를 만들때 클랙스 레벨에 있는 정보를 먼저 참고한다.
	그리고난 후 메소드 레벨에 붙어있는 정보를 추가합니다.
 */

/*
	Component라는 어노테이션으로 스프링컨테이너에 들어가는 컴포넌트야 라고 선언을 한다.
 */

// @RestController애노테이션은 @Component애노테이션을 메타 애니토에션으로 포함하고 있기때문에
// @Component애노테이션 생략을해도 Bean으로 등록이 가능합니다.

// Spring Container가 ApplicationContextAware를 구현하고있는 오브젝트에 ApplicationContext를 주집하는지 테스트
@RestController
@RequestMapping("/hello")
public class HelloController {

	private final HelloService helloService;

	private final ApplicationContext applicationContext;

	// applicationContext를 생성자를 통해서 주입도 가능합니다.
	public HelloController(HelloService helloService, ApplicationContext applicationContext) {
		this.helloService = helloService;
		this.applicationContext = applicationContext;
	}

	// GET 메소드를 사용하고 /hello 경로로 접근하는 요청을 매핑하겠다.
	@GetMapping
	/*
	 일반적으로 String으로 리턴을 했다면 String으로 전달된 값의 View가 존재하는지 확인합니다.
	 다만 현재 String으로 전달된 값의 View를 찾을 수 없으면 404 Not Found 오류가 발생하게 됩니다.

	 String값을 그대로 Web 응답에 Body에 넣어서 전달하게 하는 텍스트 플레인으로
	 전달하게 하는 방식을 사용하려면 ResponseBody를 사용하면 됩니다.

	 @ResponseBody애노테이션은 @RestController에 메타 애노테이션으로 포함하고 있기 때문에
	 생략이 가능하다
	 */

	// @ResponseBody
	// RequestMapping을 사용해도 되지만 간결하게 사용하기위해 @GetMapping로 합쳐진 애노테이션이 만들어졌다.
	// @RequestMapping(method = RequestMethod.GET, value = "/hello")
	public String hello(String name) {
		return helloService.sayHello(Objects.requireNonNull(name));
	}

	// Spring Container가 ApplicationContextAware를 구현하고있는 오브젝트에 ApplicationContext를 주집하는지 테스트
	// @Override
	// public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	// 	System.out.println(applicationContext);
	// 	this.applicationContext = applicationContext;
	// }
}
