package com.betrybe;

import com.betrybe.model.EntityCarro;
import com.google.gson.Gson;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testGetCarros() {
        given()
                .when().get("/carro")
                .then()
                .statusCode(200)
                .body(is("[]"));
    }

    @Test
    public void insertCarros() {
        EntityCarro car = new EntityCarro();
        car.setAno("1999");
        car.setNome("fusca");
        car.setPreco(1000.0);
        String json = new Gson().toJson(car);
        EntityCarro saved = given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(json)
                .post("/carro")
                .then()
                .statusCode(201)
                .extract().as(EntityCarro.class);
        Assertions.assertNotNull(saved.getId());
    }

    @Test
    public void getById() {
        EntityCarro car = new EntityCarro();
        car.setAno("1999");
        car.setNome("fusca");
        car.setPreco(1000.0);
        String json = new Gson().toJson(car);
        EntityCarro saved = given()
                .header("Content-Type", "application/json")
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(json)
                .post("/carro")
                .then()
                .statusCode(201)
                .extract().as(EntityCarro.class);
        EntityCarro returned = given()
                .when()
                .get("/carro/{id}", saved.getId())
                .then()
                .statusCode(200)
                .extract().as(EntityCarro.class);
        Assertions.assertTrue(returned.getId() > 0);
    }

}