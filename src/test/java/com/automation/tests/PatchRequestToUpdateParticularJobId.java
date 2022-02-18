package com.automation.tests;

import static io.restassured.RestAssured.given;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PatchRequestToUpdateParticularJobId extends BaseTest {

	@Test(description = "Validate the status code for PATCH request using Query param")
	public void patchRequestWithQueryParam() {
		
		Response response = given().
				spec(requestSpecification).
				accept(ContentType.JSON).
				queryParam("id", 4).
				queryParam("jobTitle", "Software Engineering").
				queryParam("jobDescription", "To develop web application").
			when().
				patch("/normal/webapi/update/details").
			then().
				extract().response();
		
		logRequestInReport(stringWriter.toString());
		logResponseInReport("API RESPONSE", response.prettyPrint());
		
		// Assert the status code
		Assert.assertEquals(response.statusCode(), 200);
	}

}
