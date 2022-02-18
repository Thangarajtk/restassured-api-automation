package com.automation.practice;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class PostApiTestDemo {

	// Available Libraries Gson, Jackson, Json and Simple Json
	@Test
	public void postRequestTest() {
		
		JSONObject request = new JSONObject();
		//POST request body
		request.put("name", "Raj");
		request.put("Job", "Automation Engineer");
		
		given().
			header("Content-Type", "application.json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toString());
		when().
			post("https://reqres.in/api/users").
		then().
			assertThat().
			statusCode(201);
	}
}
