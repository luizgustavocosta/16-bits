package com.costa.luiz;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@QuarkusTest
class HelloWorldResourceTest {

    @Test
    @DisplayName("Say hello world")
    void sayHello() {
        String nameExpected = "Luiz";
        given()
                .when()
                .get("/api/quarkus/helloworld/"+nameExpected)
                .then()
                .statusCode(200)
                .body(containsString("Hello "+nameExpected+" from Quarkus at "));
    }
}