package com.costa.luiz.quarkus.domain.item;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;

@DisplayName("Item Resource")
@QuarkusTest
@Tag("integration")
class ItemResourceTest {

    public static final String ENDPOINT = "/api/quarkus/v1/items";

    @InjectMock
    ItemRepository repository;

    @DisplayName("Find all items")
    @Test
    void findAll() {
        given()
                .when().get(ENDPOINT)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(notNullValue());
    }

    @DisplayName("Find one item")
    @Test
    void findOne() {
        Mockito.when(repository.findById(any()))
                .thenReturn(new Item("A", "B"));
        String paramName = "id";
        String paramValue = "1";
        given().pathParam(paramName, paramValue)
                .when()
                .get(ENDPOINT + "/{id}")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body(containsString("{\"description\":\"B\",\"name\":\"A\"}"));
    }

    @DisplayName("Delete one item")
    @Test
    void delete() {
        Long magicNumber = 42L;
        Mockito.when(repository.deleteById(magicNumber)).thenReturn(true);
        given().pathParam("id", magicNumber)
                .when()
                .delete(ENDPOINT + "/{id}")
                .then()
                .statusCode(HttpStatus.SC_ACCEPTED)
                .body(containsString(magicNumber + " deleted"));
    }

    @DisplayName("Create a new item")
    @Test
    void post() {
        Mockito.doNothing().when(repository).persist(any(Item.class));
        given().body("{\"id\":\"42\",\"name\":\"A\", \"description\":\"B\"}")
                .contentType("application/json")
                .when()
                .post(ENDPOINT)
                .then()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @DisplayName("Try to update an non existing item")
    @Test
    void tryUpdate() {
        given().pathParam("id", 42L)
                .pathParam("name", "A")
                .pathParam("description", "B")
                .when()
                .put(ENDPOINT + "/id/{id}/name/{name}/description/{description}")
                .then().statusCode(HttpStatus.SC_NOT_FOUND)
                .body(containsString("Item with id of 42 does not exist"));
    }

    @DisplayName("Update an item")
    @Test
    void update() {
        Long magicNumber = 42L;
        Item item = new Item("D", "C");

        Mockito.when(repository.findById(magicNumber)).thenReturn(item);
        Mockito.doNothing().when(repository).persist(item);

        given().pathParam("id", magicNumber)
                .pathParam("name", "A")
                .pathParam("description", "B")
                .when()
                .put(ENDPOINT + "/id/{id}/name/{name}/description/{description}")
                .then().statusCode(HttpStatus.SC_ACCEPTED)
                .body(containsString("{\"description\":\"B\",\"name\":\"A\"}"));
    }
}