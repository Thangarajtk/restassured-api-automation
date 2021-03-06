package com.automation.practice;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import org.json.JSONObject;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;

/**
 * PATCH request does partial update
 * Fields that need to be updated by the client, only that field is updated without modifying the other field.
 */
public class PatchRequestTestDemo {

	@Test
	public void testPatchRequest() {
		
		JSONObject request = new JSONObject();
	
		request.put("name", "Raj");
		request.put("Job", "Automation Engineer");
		
		given().
			header("Content-Type", "application.json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toString());
		when().
			patch("https://reqres.in/api/users/2").
		then().
			assertThat().
				statusCode(200).
			log().all();
	}
}
