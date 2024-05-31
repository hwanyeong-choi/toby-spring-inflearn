package com.tobyspring.tobyspringboot;

import java.util.Objects;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 DispatchServlet이 맵핑정보를 만들때 클랙스 레벨에 있는 정보를 먼저 참고한다.
 그리고난 후 메소드 레벨에 붙어있는 정보를 추가합니다.
 */

@RequestMapping("/hello")
public class HelloController {

	private final HelloService helloService;

	public HelloController(HelloService helloService) {
		this.helloService = helloService;
	}

	// GET 메소드를 사용하고 /hello 경로로 접근하는 요청을 매핑하겠다.
	@GetMapping
	/*
	 일반적으로 String으로 리턴을 했다면 String으로 전달된 값의 View가 존재하는지 확인합니다.
	 다만 현재 String으로 전달된 값의 View를 찾을 수 없으면 404 Not Found 오류가 발생하게 됩니다.

	 String값을 그대로 Web 응답에 Body에 넣어서 전달하게 하는 텍스트 플레인으로
	 전달하게 하는 방식을 사용하려면 ResponseBody를 사용하면 됩니다.
	 */

	@ResponseBody
	// RequestMapping을 사용해도 되지만 간결하게 사용하기위해 @GetMapping로 합쳐진 애노테이션이 만들어졌다.
	// @RequestMapping(method = RequestMethod.GET, value = "/hello")
	public String hello(String name) {
		return helloService.sayHello(Objects.requireNonNull(name));
	}

}
