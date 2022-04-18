package com.automation.practice;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class DataDrivenTestDemo {
	
	@DataProvider(name = "test_data")
	public Object[][] dataForPostRequest() {

		Object[][] objects = {
				{"Appium", 2},
				{"Docker", 1},
				{"Jenkins CI", 1}
		};
		return objects;
	}

	@Test(dataProvider = "test_data", enabled = false)
	public void postRequest(String firstName, int subjectId) {

		JSONObject request = new JSONObject();
		request.put("firstName", firstName);
		request.put("subjectId", subjectId);

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
	
	@DataProvider(name = "deleteTestData")
	public Object[] dataForDeleteRequest() {

		return new Object[] {
				1,2,7,8,9
		};
	}
	
	@Test(dataProvider = "deleteTestData")
	public void deleteRequest(int id) {

		baseURI = "http://localhost:3000/";
		
		given().
		when().
			delete("/users/"+id).
		then().
			statusCode(200);
	}
}
