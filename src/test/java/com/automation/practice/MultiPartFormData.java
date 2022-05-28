package com.automation.practice;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class MultiPartFormData {

    @Test
    public void testMultiPartFormData() {
        given().
                baseUri("https://postman-echo.com").
                    multiPart("foo1", "bar1").
                    log().all().
                when().
                    post("/post").
                then().
                    log().all().
                    assertThat().
                    statusCode(200);
    }
}
