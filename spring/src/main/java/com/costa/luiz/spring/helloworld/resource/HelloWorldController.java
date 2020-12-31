package com.costa.luiz.spring.helloworld.resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class HelloWorldController {

    @GetMapping("/api/spring/helloworld/{name}")
    public String sayHelloWorld(@PathVariable("name") String name) {
        return "Hello World "+name+" at "+ LocalDateTime.now() +"\n";
    }
}
