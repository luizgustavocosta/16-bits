package com.costa.luiz.spring.helloworld;

import com.costa.luiz.spring.resource.HelloWorldController;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SmokeTest implements WithAssertions {

    @Autowired
    private HelloWorldController controller;

    @Test
    void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}
