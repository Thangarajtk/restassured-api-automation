package com.automation.practice.reqres;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public final class PathParameterTest {

  @Test
  public void testPathParameter() {
    given().
      baseUri("https://reqres.in/").
      pathParam("userId", "2").
      log().all().
      when().
      get("/api/users/{userId}").
      then().
      log().all().
      assertThat().
      statusCode(200);
  }
}
