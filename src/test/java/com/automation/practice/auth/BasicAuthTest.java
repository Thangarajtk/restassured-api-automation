package com.automation.practice.auth;

import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BasicAuthTest {

    @Test
    public void basicAuth() {
        Response response = given()
                .auth()
                .basic("postman", "password")
                .log().all()
                .get("https://postman-echo.com/basic-auth");

        response.prettyPrint();
    }
}
