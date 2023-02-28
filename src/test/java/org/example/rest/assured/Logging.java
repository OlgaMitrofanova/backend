package org.example.rest.assured;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class Logging extends AbstractTest{
    @BeforeAll
    static void setUp(){

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

    }

    @Test
    void getRequestLogTest() {
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .log().method()
                .log().params()
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information");

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++=");

        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .log().all()
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information");
    }

    @Test
    void getResponseLogTest(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .log().all()
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information")
                .prettyPeek();
    }

    @Test
    void getErrorTest(){
        given()
                .queryParam("apiKey", getApiKey())
                .queryParam("includeNutrition", "false")
                .when()
                .get("https://api.spoonacular.com/recipes/716429/information")
                .then().statusCode(201);
    }
}
