package com.automation.practice;

import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

public class CustomApiTestDemo {

	// GET Request
	@Test
	public void getTest() {

		baseURI = "http://localhost:3000/";

		given().
			param("firstName", "Auto Test").
		when().
			get("/users").
		then().
			assertThat().
			statusCode(200).
			log().all();
	}

	@SuppressWarnings("unchecked")
	@Test(enabled = false)
	public void postRequest() {

		JSONObject request = new JSONObject();
		request.put("firstName", "Automation Testing");
		request.put("subjectId", 1);

		baseURI = "http://localhost:3000/";
		
		given().
			header("Content-Type", "application.json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			post("/users").
		then().
			assertThat().
			statusCode(201);
	}
	
	@SuppressWarnings("unchecked")
	@Test(enabled = false)
	public void patchRequest() {

		JSONObject request = new JSONObject();
		
		request.put("firstName", "DevOps Automation");

		baseURI = "http://localhost:3000/";
		
		given().
			header("Content-Type", "application.json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			patch("/users/4").
		then().
			assertThat().
			statusCode(200).
			log().all();
	}

	@Test(enabled = false)
	public void putRequest() {

		JSONObject request = new JSONObject();
		
		request.put("firstName", "DevOps Engineer");
		request.put("subjectId", 2);

		baseURI = "http://localhost:3000/";
		
		given().
			header("Content-Type", "application.json").
			contentType(ContentType.JSON).
			accept(ContentType.JSON).
			body(request.toJSONString()).
		when().
			put("/users/4").
		then().
			assertThat().
			statusCode(200);
	}
	
	@Test
	public void deleteRequest() {

		baseURI = "http://localhost:3000/";
		
		given().
		when().
			delete("/users/4").
		then().
			assertThat().
			statusCode(200);
	}
}
