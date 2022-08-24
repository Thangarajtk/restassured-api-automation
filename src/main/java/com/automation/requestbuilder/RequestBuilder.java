package com.automation.requestbuilder;

import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static io.restassured.RestAssured.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RequestBuilder {

    public static RequestSpecification buildRequest() {
        return given().baseUri("http://localhost:3000")
                .header("Content-Type", "application/json");
    }
}
