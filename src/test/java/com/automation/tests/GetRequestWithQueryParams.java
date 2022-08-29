package com.automation.tests;

import static io.restassured.RestAssured.given;

import com.automation.base.BaseTest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import junit.framework.Assert;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GetRequestWithQueryParams extends BaseTest {

	@Test(description="Validate the status code for GET request using query params")
	public void getParticularJobDetailsUsingQueryParams() {
		
		Response response = given().
				spec(requestSpecification).
				headers("Content-Type", "application/json").
				queryParam("id", 1).
				queryParam("jobTitle", "Software Engg").
			when().
				get("/normal/webapi/find").
			then().
				extract().response();
		
		logRequestInReport(stringWriter.toString());
		logResponseInReport("API RESPONSE", response.prettyPrint());
		
		Assert.assertEquals(response.getStatusCode(), 200);
	}

}
