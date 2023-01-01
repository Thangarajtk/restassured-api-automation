package com.automation.practice.jsonserver;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public final class UpdateRequestTest {

  @Test
  public void updateTest() {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("first_name", "firstname");
    jsonObject.put("last_name", "lastname");

    Response response = given()
      .baseUri("http://localhost:3000")
      .pathParam("id", 13)
      .header("Content-Type", "application/json")
      .body(jsonObject.toString())
      .when()
      .put("/employees/{id}");

    response.prettyPrint();

  }
}
