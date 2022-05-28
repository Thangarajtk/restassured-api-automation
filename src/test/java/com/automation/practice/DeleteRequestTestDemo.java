package com.automation.practice;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class DeleteRequestTestDemo {

    @Test
    public void testDeleteRequest() {

        given().
                when().
                delete("https://reqres.in/api/users/2").
                then().
                assertThat().
                statusCode(204). // Status code should be 204 for Delete
                log().all();
    }
}
