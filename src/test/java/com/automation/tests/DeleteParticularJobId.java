package com.automation.tests;

import static io.restassured.RestAssured.*;

import com.automation.base.BaseTest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DeleteParticularJobId extends BaseTest {

    @Test(description = "Validate the status code for DELETE request")
    public void deleteSpecificRecordUsingDeleteRequest() {

        Response response = given().
                	spec(requestSpecification).
                	accept(ContentType.JSON).
                	contentType(ContentType.JSON).
                when().
                	delete("/normal/webapi/remove/3").
                then().
                	extract().response();

        logRequestInReport(stringWriter.toString());
        logResponseInReport("API RESPONSE", response.prettyPrint());

        // Assert the status code
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test(description = "Validate the DELETE request with Path param")
    public void deleteRequestWithPathParam() {

        Response response = given().
                spec(requestSpecification).
                accept(ContentType.JSON).
                contentType(ContentType.JSON).
                pathParam("id", 5).
                when().
                delete("/normal/webapi/remove/{id}").
                then().
                extract().response();

        logRequestInReport(stringWriter.toString());
        logResponseInReport("API RESPONSE", response.prettyPrint());

        // Assert the status code
        Assert.assertEquals(response.statusCode(), 200);
    }
}
