package com.tobyspring.tobyspringboot;

import org.springframework.stereotype.Service;

// @Service어노테이션은 메타 애노테이션으로 ComponentScan을 포함하고 있으므로 생략이 가능합니다.
@Service
public class SimpleHelloService implements HelloService {

	@Override
	public String sayHello(String name) {
		return "Hello " + name;
	}

}
