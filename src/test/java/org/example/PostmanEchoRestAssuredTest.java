package org.example;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostmanEchoRestAssuredTest {

    private final String baseUrl = "https://postman-echo.com";

    @Test
    public void testGetMethod() {
        Map<String, String> params = new HashMap<>();
        params.put("foo1", "bar1");
        params.put("foo2", "bar2");

        Response response = given()
                .queryParams(params)
                .when()
                .get(baseUrl + "/get");

        assertEquals(200, response.getStatusCode());

        String param1 = response.jsonPath().getString("args.foo1");
        String param2 = response.jsonPath().getString("args.foo2");
        assertEquals("bar1", param1);
        assertEquals("bar2", param2);
    }

    @Test
    public void testPostMethod() {
        Map<String, String> body = new HashMap<>();
        body.put("name", "Nika");
        body.put("course", "QA");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post(baseUrl + "/post");

        assertEquals(200, response.getStatusCode());

        String name = response.jsonPath().getString("json.name");
        assertEquals("Nika", name);
    }

    @Test
    public void testPutMethod() {
        String requestBody = "This is expected to be sent back as part of response body.";

        Response response = given()
                .body(requestBody)
                .when()
                .put(baseUrl + "/put");

        assertEquals(200, response.getStatusCode());

        String data = response.jsonPath().getString("data");
        assertEquals(requestBody, data);
    }

    @Test
    public void testPatchMethod() {
        Map<String, String> body = new HashMap<>();
        body.put("name", "Nika");
        body.put("course", "QA");

        Response response = given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .patch(baseUrl + "/patch");

        assertEquals(200, response.getStatusCode());

        String name = response.jsonPath().getString("json.name");
        assertEquals("Nika", name);
    }

    @Test
    public void testDeleteMethod() {
        String requestBody = "This is expected to be sent back as part of response body.";

        Response response = given()
                .body(requestBody)
                .when()
                .delete(baseUrl + "/delete");

        assertEquals(200, response.getStatusCode());

        String data = response.jsonPath().getString("data");
        assertEquals(requestBody, data);
    }
}