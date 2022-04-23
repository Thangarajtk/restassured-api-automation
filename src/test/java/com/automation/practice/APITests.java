package com.automation.practice;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;
import com.automation.base.BaseTest;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;

public class APITests extends BaseTest {

    @Test
    public void getNumberOfCircuits() {

        Response response =
                given().
                        filter(new RequestLoggingFilter(printStream)).  // This line is mandatory to log the request details to extent report
                        when().
                        get("http://ergast.com/api/f1/2017/circuits.json").
                        then().
                        statusCode(200).
                        extract().response();
        System.out.println(response.asString());
    }
}
