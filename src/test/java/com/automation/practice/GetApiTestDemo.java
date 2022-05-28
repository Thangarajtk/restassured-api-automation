package com.automation.practice;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class GetApiTestDemo {

	@Test
	public void getNumberOfCircuits() {
		given().
		when().
			get("http://ergast.com/api/f1/2017/circuits.json").
		then().
			assertThat().
			statusCode(200).
			and().
				body("MRData.CircuitTable.Circuits.circuitId", hasSize(20)).
			and().
				header("content-length", equalTo("4551"));
	}

	@Test(enabled = false)
	public void preemptiveBasicAuthTest() {
		given().
			auth().preemptive().basic("", "").
		when().
			get("").
		then().
			assertThat().statusCode(200);
	}

	@Test(enabled = false)
	public void oauthTest() {
		given().
			auth().oauth("", "", "", "").
		when().
			post("").
		then().
			assertThat().statusCode(201);
	}
	
}
