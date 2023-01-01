package com.automation.practice.postmanecho;

import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public final class QueryParametersTest {

  @Test
  public void testSingleQueryParam() {
    given().
      baseUri("https://postman-echo.com").
      queryParam("key1", "value1").
      when().
      get("/get").
      then().
      log().all().
      assertThat().
      statusCode(200);
  }

  @Test
  public void testMultipleQueryParam() {
    HashMap<String, String> queryParameters = new HashMap<>();
    queryParameters.put("key1", "value1");
    queryParameters.put("Key2", "value2");
    given().
      baseUri("https://postman-echo.com").
      queryParams(queryParameters).
      when().
      get("/get").
      then().
      log().all().
      assertThat().
      statusCode(200);
  }

  @Test
  public void testMultipleValueQueryParam() {
    given().
      baseUri("https://postman-echo.com").
      queryParam("key1", "value1", "value2", "value3").
      when().
      get("/get").
      then().
      log().all().
      assertThat().
      statusCode(200);
  }
}
