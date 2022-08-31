package com.automation.tests;

import static com.automation.reports.ExtentLogger.logRequest;
import static com.automation.reports.ExtentLogger.logResponse;
import static io.restassured.RestAssured.given;

import com.automation.base.BaseTest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GetRequestWithBasicAuthTest extends BaseTest {

    /**
     * Basic Authentication
     * 1) Preemptive
     * 2) Challanged
     */
    @Test(description = "Validate the status code for secure GET request with Basic Authentication")
    public void secureGetRequestUsingChallengedBasicAuth() {
        Response response = given().
                spec(requestSpecification).
                accept(ContentType.JSON).
                auth().basic("admin", "welcome").
                when().
                get("/secure/webapi/all").
                then().
                extract().response();

        logRequest(requestSpecification);
        logResponse(response.asPrettyString());

        Assert.assertEquals(response.statusCode(), 200);
    }
}
