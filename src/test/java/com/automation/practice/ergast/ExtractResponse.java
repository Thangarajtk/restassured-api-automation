package com.automation.practice.ergast;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

import org.testng.annotations.Test;
import com.automation.base.BaseTest;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.response.Response;

public class ExtractResponse extends BaseTest {

    @Test
    public void testExtractResponse() {
        Response response = given().
                            baseUri("http://ergast.com").
                            filter(new RequestLoggingFilter(printStream)).  // This line is mandatory to log the request details to extent report
                        when().
                            get("/api/f1/2017/circuits.json").
                        then().
                            statusCode(200).
                            extract().response();

        logResponseInReport("RESPONSE", response.asString());
    }

    @Test
    public void getNumberOfCircuits() {
        given().
                baseUri("http://ergast.com").
        when().
                get("/api/f1/2017/circuits.json").
        then().
                assertThat().
                statusCode(200).
                and().
                body("MRData.CircuitTable.Circuits.circuitId", hasSize(20)).
                and().
                header("content-length", equalTo("4551"));
    }
}
