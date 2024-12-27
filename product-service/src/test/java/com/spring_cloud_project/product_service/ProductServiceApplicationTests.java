package com.spring_cloud_project.product_service;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.shaded.org.hamcrest.Matchers;

@Import(TestcontainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductServiceApplicationTests {

    @ServiceConnection
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

    @LocalServerPort
    private Integer port;

    @BeforeEach
    void setUp() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    static {
        mongoDBContainer.start();
    }

    @Test
    void shouldCreateProduct() {
        String requestBody = """
                {
                	"name": "Product C",
                		"description": "Portable power bank with 10,000mAh capacity and fast charging.",
                		"price": 29.99
                }
                """;

        RestAssured.given()
                .contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/v1/product")
                .then()
                .statusCode(201)
                .body("id", (ResponseAwareMatcher<Response>) Matchers.notNullValue())
                .body("name", (ResponseAwareMatcher<Response>) Matchers.equalTo("Product C"))
                .body("description", (ResponseAwareMatcher<Response>) Matchers.equalTo("Portable power bank with 10,000mAh capacity and fast charging."))
                .body("price", (ResponseAwareMatcher<Response>) Matchers.equalTo(29.99));
    }


}
