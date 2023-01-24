package com.automation.practice.ergast;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.automation.reports.ExtentLogger.logResponseInReport;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

public final class ExtractResponseTest {

  @Test
  public void testExtractResponse() {
    Response response = given().
      baseUri("http://ergast.com").
      when().
      get("/api/f1/2017/circuits.json").
      then().
      statusCode(200).
      extract().response();

    logResponseInReport("RESPONSE", response.asString());
  }

  @Test
  public void getNumberOfCircuits() {
    given().
      baseUri("http://ergast.com").
      when().
      get("/api/f1/2017/circuits.json").
      then().
      assertThat().
      statusCode(200).
      and().
      body("MRData.CircuitTable.Circuits.circuitId", hasSize(20)).
      and().
      header("content-length", equalTo("4551"));
  }
}
