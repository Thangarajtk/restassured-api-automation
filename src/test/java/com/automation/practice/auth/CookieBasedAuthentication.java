package com.automation.practice.auth;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public final class CookieBasedAuthentication {

  @Test
  public void testCookieBasedAuthentication() {
    JSONObject request = new JSONObject();
    // POST request body
    request.put("username", "User");
    request.put("password", "test@123");

    Response response = given()
      .header("Content-Type", ContentType.JSON)
      .body(request)
      .post("http://localhost:8086/rest/auth/1/session");

    System.out.println(response.getStatusCode());
    System.out.println(response.getBody().jsonPath().prettify());

    String sessionID = response.getCookies().get("JSESSIONID");

    given().contentType(ContentType.JSON)
      .cookie("JSESSIONID", sessionID)
      .body("")
      .post("http://localhost:8086/rest/api/2/issue/");
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
