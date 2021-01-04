package com.costa.luiz;

import io.quarkus.test.junit.QuarkusTest;
import org.apache.http.HttpStatus;
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
        String paramName = "name";
        given().pathParam(paramName, nameExpected)
                .when().get("/api/quarkus/helloworld/{"+paramName+"}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(containsString("Quarkus saying 'Hello to " + nameExpected + "', from"));
    }
}