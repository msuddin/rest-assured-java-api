package com.thetestroom;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void shouldGetStringOfHi() {
        String name = "QAShahin";

        given()
            .pathParam("name", name)
        .when()
            .get("hi/{name}")
        .then()
            .body(is(String.format("Hi %s", name)));
    }

    @Test
    public void shouldGetPersonName() {
        String name = "QAShahin";

        given()
            .param("name", name)
        .when()
            .get("/hello")
        .then()
            .body("name", is(name));
    }

    @Test
    public void shouldGetPersonObject() {
        given()
            .contentType("application/json")
            .body(new Person("qashahin", 25))
        .when()
            .post("/person")
        .then()
            .body("name", is("qashahin"))
            .body("age", is(25));
    }
}
