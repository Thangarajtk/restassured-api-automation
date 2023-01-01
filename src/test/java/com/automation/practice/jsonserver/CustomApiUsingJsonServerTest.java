package com.automation.practice.jsonserver;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public final class CustomApiUsingJsonServerTest {

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
  public Object[][] dataForDeleteRequest() {
    return new Object[][] {
      {1}, {2}, {7}, {8}, {9}
    };
  }

  @Test(dataProvider = "deleteTestData")
  public void deleteRequest(int id) {
    baseURI = "http://localhost:3000/";

    given().
      when().
      delete("/users/" + id).
      then().
      statusCode(200);
  }
}
