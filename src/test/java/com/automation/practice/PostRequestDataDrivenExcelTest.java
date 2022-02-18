package com.automation.practice;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.utils.ExcelUtils;

import io.restassured.http.ContentType;


public class PostRequestDataDrivenExcelTest {

	@DataProvider(name = "input")
	public Object[][] getPostData() {
		Object[][] data = null;
		ExcelUtils xlReader = new ExcelUtils();
		HashMap<String, ArrayList<Object>> map = xlReader.getExcelData();
		
		ArrayList<Object> value = map.get("FirstName");
		data = new Object[value.size()][2];

		for (int i = 0; i < value.size(); i++) {
			data[i][0]=map.get("FirstName").get(i);
			data[i][1]=map.get("SubjectId").get(i);
		}
		return data;
	}

	@SuppressWarnings("unchecked")
	@Test(dataProvider="input")
	public void postRequest(String firstName, String subjectId) {

		JSONObject request = new JSONObject();
		request.put("firstName", firstName);
		request.put("subjectId", Integer.parseInt(subjectId));

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
}
