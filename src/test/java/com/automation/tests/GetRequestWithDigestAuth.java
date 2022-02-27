package com.automation.tests;

import static io.restassured.RestAssured.given;

import com.automation.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetRequestWithDigestAuth extends BaseTest {
	
	/**
	 * Digest Authentication
	 */
	@Test(description="Validate the status code for secure GET request with Digest Authentication")
	public void getRequestUsingDigestAuth() {
		
		Response response = given().
				baseUri("http://postman-echo.com").
				accept(ContentType.JSON).
				contentType(ContentType.JSON).
				auth().digest("postman", "password").
			when().
				get("/digest-auth").
			then().
				extract().response();
		
		logRequestInReport(stringWriter.toString());
		logResponseInReport("API RESPONSE", response.prettyPrint());
		
		Assert.assertEquals(response.statusCode(), 200);
	}

}
