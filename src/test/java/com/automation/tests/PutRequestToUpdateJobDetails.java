package com.automation.tests;

import static io.restassured.RestAssured.given;
import java.io.File;

import com.automation.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.response.Response;

public class PutRequestToUpdateJobDetails extends BaseTest {

	@Test(description="Validate the status code for PUT request")
	public void updateUsingPutRequest() {
		
		File file = new File(System.getProperty("user.dir")+"/src/test/resources/json/update_job_details.json");
		
		Response response = given().
				spec(requestSpecification).
				headers("Content-Type", "application/json", "Accept", "application/json").
				body(file).
			when().
				put("/normal/webapi/update").
			then().
				extract().response();
		
		logRequestInReport(stringWriter.toString());
		logResponseInReport("API RESPONSE", response.prettyPrint());
		
		Assert.assertEquals(response.statusCode(), 200);
	}
}
