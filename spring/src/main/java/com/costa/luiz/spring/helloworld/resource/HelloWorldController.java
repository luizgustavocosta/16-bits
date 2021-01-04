package com.costa.luiz.spring.helloworld.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/spring/helloworld")
public class HelloWorldController {

    @Autowired
    Environment environment;

    @Value("${server.port}")
    String port;


    @GetMapping("{name}")
    public String sayHello(@PathVariable("name") String name) {
        try {
            return String.format("Spring saying 'Hello to %s', from %s:%s. Message created on %s",
                    name,
                    InetAddress.getLocalHost().getHostName(),
                    port,
                    LocalDateTime.now());
        } catch (UnknownHostException exception) {
            throw new IllegalStateException("Local host name could not be resolved into an address. " +
                    "Exception message ["+exception.getMessage()+"]");
        }
    }
}
