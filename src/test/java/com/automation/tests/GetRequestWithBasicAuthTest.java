package com.automation.tests;

import com.automation.annotations.FrameworkAnnotation;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.automation.enums.Authors.USER_2;
import static com.automation.enums.CategoryType.SMOKE;
import static com.automation.models.builders.RequestBuilder.createRequestSpecification;
import static com.automation.reports.ExtentLogger.logRequest;
import static com.automation.reports.ExtentLogger.logResponse;
import static io.restassured.RestAssured.given;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GetRequestWithBasicAuthTest {

  /**
   * Basic Authentication
   * 1) Preemptive
   * 2) Challanged
   */
  @FrameworkAnnotation(author = USER_2, category = {SMOKE})
  @Test(description = "Validate the status code for secure GET request with Basic Authentication")
  public void secureGetRequestUsingChallengedBasicAuth() {
    RequestSpecification requestSpecification = createRequestSpecification();
    Response response = given().
      spec(requestSpecification).
      accept(ContentType.JSON).
      auth().basic("admin", "welcome").
      when().
      get("/secure/webapi/all").
      then().
      extract().response();

    logRequest(requestSpecification);
    logResponse(response.asPrettyString());

    Assert.assertEquals(response.statusCode(), 200);
  }
}
