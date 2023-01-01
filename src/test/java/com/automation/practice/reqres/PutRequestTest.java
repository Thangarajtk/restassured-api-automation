package com.automation.practice.reqres;

import io.restassured.http.ContentType;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public final class PutRequestTest {

  @Test
  public void testPutRequest() {

    JSONObject request = new JSONObject();

    request.put("name", "Raj");
    request.put("Job", "Automation Engineer");

    given().
      header("Content-Type", "application.json").
      contentType(ContentType.JSON).
      accept(ContentType.JSON).
      body(request.toString());
    when().
      put("https://reqres.in/api/users/2").
      then().
      assertThat().
      statusCode(200).
      log().all();
  }

}
