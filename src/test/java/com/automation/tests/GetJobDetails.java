package com.automation.tests;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.automation.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.automation.reports.ExtentLogger;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class GetJobDetails extends BaseTest {

	@Test(description="Validate the status code for GET request")
	public void getRequestToValidateStatusCode() {
		
		Response response = given().
			spec(requestSpecification).
		when().
			get("/normal/webapi/all").
		then().
			log().all(). // All Response logging
			log().ifError(). // Log response only if error
			log().ifValidationFails(). // Log response only if there is failure in validation
			extract().response();
		
		logRequestInReport(stringWriter.toString());
		logResponseInReport("API RESPONSE", response.prettyPrint());
			
		response.then().assertThat().statusCode(200);
	}
	
	@Test(description="Validate the JSON response body for GET request")
	public void getRequestToValidateJsonResponseBody() {
		
		Response response = given().
			spec(requestSpecification).
		when().
			get("/normal/webapi/all").
		then().
			spec(responseSpecification).
			extract().response();
		
		logRequestInReport(stringWriter.toString());
		logResponseInReport("API RESPONSE", response.prettyPrint());
			
		// Hamcrest Matchers assertion
		response.then().assertThat().body("[0].jobTitle", equalTo("Software Engg"), 
				"[0].project[0].technology", hasItem("Kotlin"),
				"[0].jobId", equalTo(1));
	}
	
	@Test(description="Validate the XML response body for GET request")
	public void getRequestToValidateXmlResponseBody() {
		
		Response response = given().
			spec(requestSpecification).
			header("Accept", "application/xml").
		when().
			get("/normal/webapi/all").
		then().
			extract().response();
		
		logRequestInReport(stringWriter.toString());
		logResponseInReport("API RESPONSE", response.prettyPrint());
			
		response.then().assertThat().body("List.item.jobTitle", equalTo("Software Engg"), 
				"List.item.project.project.technology.technology", hasItem("Kotlin"),
				"List.item.jobId", equalTo("1"));
	}
	
	@Test(description="Validate the JSON response body using Json Path for GET request")
	public void getRequestToValidateResponseBodyUsingJsonPath() {
		
		Response response = given().
			spec(requestSpecification).
		when().
			get("/normal/webapi/all").
		then().
			extract().response();
		
		String jobTitle = response.jsonPath().get("[0].jobTitle");
		
		logRequestInReport(stringWriter.toString());
		logResponseInReport("API RESPONSE", response.prettyPrint());
		logResponseInReport("API RESPONSE BODY", jobTitle);
		
		// TestNG Assertion
		Assert.assertEquals(jobTitle, "Software Engg");
	}
	
	@Test(description="Validate the JSON response header for GET request")
	public void getRequestToValidateJsonResponseHeader() {
		
		Response response = given().
			spec(requestSpecification).
		when().
			get("/normal/webapi/all");
			
		Headers responseHeaders = response.then().extract().headers();
		
		logRequestInReport(stringWriter.toString());
		logResponseInReport("API RESPONSE", response.prettyPrint());
		logResponseInReport("API RESPONSE HEADER", responseHeaders.toString());
			
		response.then().assertThat().header("content-type", equalTo("application/json"));
	}
	
	@Test(description="Validate the XML response header for GET request")
	public void getRequestToValidateXmlResponseHeader() {
		
		Response response = given().
			spec(requestSpecification).
			header("Accept", "application/xml").
		when().
			get("/normal/webapi/all").
		then().
			extract().response();
		
		Headers responseHeaders = response.then().extract().headers();
		
		logRequestInReport(stringWriter.toString());
		logResponseInReport("API RESPONSE", response.prettyPrint());
		logResponseInReport("API RESPONSE HEADER", responseHeaders.toString());
			
		response.then().assertThat().header("content-type", equalTo("application/xml"));
	}
	
	@Test(description="Validate the status line for GET request")
	public void getRequestToValidateStatusLine() {
		
		Response response = given().
			spec(requestSpecification).
		when().
			get("/normal/webapi/al");
			
		String actualStatusLine = response.then().extract().statusLine();
		
		logRequestInReport(stringWriter.toString());
		logResponseInReport("API RESPONSE", response.prettyPrint());
		logResponseInReport("API RESPONSE STATUS LINE", actualStatusLine.toString());
			
		Assert.assertEquals(actualStatusLine.trim(), "HTTP/1.1 404");
		ExtentLogger.pass("Expected Status line is HTTP/1.1 404 but actual was " + actualStatusLine.trim());
	}
}
