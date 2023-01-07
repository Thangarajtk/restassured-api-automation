package com.automation.practice.auth;

import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.apache.logging.log4j.LogManager.getLogger;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BasicAuthTest {

  private static final Logger LOG = getLogger();

  @Test
  public void basicAuth() {
    Response response = given()
      .auth()
      .basic("postman", "password")
      .log().all()
      .get("https://postman-echo.com/basic-auth");

    response.prettyPrint();
    LOG.debug(response);
  }
}
