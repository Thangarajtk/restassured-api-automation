package com.automation.practice.auth;

import com.automation.reports.ExtentLogger;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DigestAuthTest {

  /**
   * Digest Authentication
   */
  @Test(description = "Validate the status code for secure GET request with Digest Authentication")
  public void getRequestUsingDigestAuth() {

    Response response = given().
      baseUri("http://postman-echo.com").
      accept(ContentType.JSON).
      contentType(ContentType.JSON).
      auth().digest("postman", "password").
      config(config.logConfig(LogConfig.logConfig().blacklistHeader("Accept"))).
      when().
      get("/digest-auth").
      then().
      extract().response();

    ExtentLogger.logResponse(response.asPrettyString());

    Assert.assertEquals(response.statusCode(), 200);
  }

}
