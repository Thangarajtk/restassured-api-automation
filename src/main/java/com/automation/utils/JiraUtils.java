package com.automation.utils;

import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static io.restassured.RestAssured.given;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JiraUtils {

  public static void createIssue() {

    String requestBody = ApiUtils.readJsonAndGetAsString("");

    Response response = given()
      .auth().basic("<<username>>", "<<password>>")
      .header("Content-Type", "application/json")
      .log().all()
      .body(requestBody)
      .post("http://localhost:8080/rest/api/2/issue/");

    response.prettyPrint();
  }
}
