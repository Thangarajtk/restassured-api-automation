package com.automation.practice.wiremock;

import io.restassured.RestAssured;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.lessThan;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SpecifyingRequestPortTest {

  @BeforeClass
  public static void defaultConfigurationSetup() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = 9091;
  }

  @Test
  public void specifyPort() {
    when().
      get("/send-me-file").
      then().
      log().all().
      statusCode(200);
  }

  /**
   * Wiremock service is used in this test
   */
  @Test
  public void getRequestUsingMockService() {
    when().
      get("/mockservice").
      then().
      log().all().
      statusCode(200);
  }

  @Test
  public void validateResponseTime() {
    when().
      get("/mockservice").
      then().
      log().all().
      time(lessThan(1200L), TimeUnit.MILLISECONDS).
      statusCode(200);
  }
}
