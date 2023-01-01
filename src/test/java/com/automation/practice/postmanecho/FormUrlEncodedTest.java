package com.automation.practice.postmanecho;

import io.restassured.config.EncoderConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public final class FormUrlEncodedTest {

  @Test
  public void testFormUrlEncoded() {
    given().
      baseUri("https://postman-echo.com").
      config(RestAssuredConfig.config().encoderConfig(EncoderConfig.encoderConfig()
                                                        .appendDefaultContentCharsetToContentTypeIfUndefined(false))).
      formParam("key1", "value1").
      formParam("key 2", "value 2").
      filter(new RequestLoggingFilter(LogDetail.ALL)).
      when().
      post("/post").
      then().
      log().all().
      assertThat().
      statusCode(200);
  }
}
