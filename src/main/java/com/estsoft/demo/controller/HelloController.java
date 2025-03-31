package com.estsoft.demo.controller;

import com.estsoft.demo.service.HelloService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @RequiredArgsConstructor // lombok
@RestController
public class HelloController {
    // = new HelloService로 객체 생성하지 않아도 어노테이션 사용해서 생성!
    // @Service 어노테이션 선언해서 스프링에 역할을 맡김
    // @Autowired // Dependency Injection -> 생성자 함수는 불필요!
    private final HelloService helloService;

    // 생성자 함수 받아     // _> 매개변수로 의존성 주입 = DI
    public HelloController(HelloService helloService) {
        // this.helloService = new HelloServece(); // 일반적인 흐름, 제어
        this.helloService = helloService; // 제어의 역전(IoC)
    }

    // @Service 어노테이션 사용
    @GetMapping("/hello")
    public String hello() {
        // service 메소드 호출
        return helloService.sayHello();
    }
}