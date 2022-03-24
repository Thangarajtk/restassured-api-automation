package com.automation.practice;

import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class CookieBasedAuth {

	@Test(enabled=false)
	public void testCookieBasedAuthentication() {
		JSONObject request = new JSONObject();
		//POST request body
		request.put("username", "User");
		request.put("password", "test@123");
		
		
		Response res = given()
			.header("Content-Type", "application/json")
			.body(request)
			.post("http://localhost:8086/rest/auth/1/session");
		
		System.out.println(res.getStatusCode());
		System.out.println(res.getBody().jsonPath().prettify());
		String sessionID = res.getCookies().get("JSESSIONID");
		
		given().contentType(ContentType.JSON)
		.cookie("JSESSIONID", sessionID)
		.body("")
		.post("http://localhost:8086/rest/api/2/issue/");	
	}
}
