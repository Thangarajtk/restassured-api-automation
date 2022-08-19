package com.automation.utils;

import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static io.restassured.RestAssured.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ApiUtils {

    public static RequestSpecification buildGetRequest() {
        return given().baseUri("http://localhost:3000")
                .header("Content-Type", "application/json");
    }
}
