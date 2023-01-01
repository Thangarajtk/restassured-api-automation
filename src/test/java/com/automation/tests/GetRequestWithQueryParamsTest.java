package com.automation.tests;

import com.automation.annotations.FrameworkAnnotation;
import com.automation.base.BaseTest;
import io.restassured.response.Response;
import junit.framework.Assert;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.annotations.Test;

import static com.automation.enums.Authors.USER_2;
import static com.automation.enums.CategoryType.SMOKE;
import static com.automation.reports.ExtentLogger.logRequest;
import static com.automation.reports.ExtentLogger.logResponse;
import static io.restassured.RestAssured.given;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GetRequestWithQueryParamsTest extends BaseTest {

  @FrameworkAnnotation(author = USER_2, category = {SMOKE})
  @Test(description = "Validate the status code for GET request using query params")
  public void getParticularJobDetailsUsingQueryParams() {

    Response response = given().
      spec(requestSpecification).
      headers("Content-Type", "application/json").
      queryParam("id", 1).
      queryParam("jobTitle", "Software Engg").
      when().
      get("/normal/webapi/find").
      then().
      extract().response();

    logRequest(requestSpecification);
    logResponse(response.asPrettyString());

    Assert.assertEquals(response.getStatusCode(), 200);
  }
}
