package com.automation.practice.auth;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public final class BearerTokenTest {

  @Test
  public void getRepositories() {
    given()
      .header("Authorization", "Bearer ")
      .queryParam("per_page", 1)
      .log().all()
      .get("https://api.github.com/user/repos")
      .prettyPrint();
  }
}
