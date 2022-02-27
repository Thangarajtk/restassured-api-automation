package com.automation.practice;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.automation.base.BaseTest;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class GetAPITest02 extends BaseTest{

	@BeforeClass
	public void beforeTest()
	{
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		requestSpecification = RestAssured.given();

		response = requestSpecification.request(Method.GET, "/Bangalore");	
	}

	@Test
	public void GetWeatherDetailsForBlr()
	{   
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is =>  " + responseBody);

		int statusCode = response.getStatusCode();
		System.out.println("Status code:"+statusCode);

		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = response.jsonPath();
		String city = jsonPathEvaluator.get("City");
		System.out.println(city);
		String humidity = jsonPathEvaluator.get("Humidity");
		String temperature = jsonPathEvaluator.get("Temperature");

		/*obj.writeData("TC02","ResponseBody", responseBody);
		obj.writeData("TC02","City", city);
		obj.writeData("TC02","Humidity", humidity);
		obj.writeData("TC02","Temperature", temperature);*/

	}
}