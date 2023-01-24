package com.automation.tests;

import com.automation.annotations.FrameworkAnnotation;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.annotations.Test;

import static com.automation.enums.Authors.USER_2;
import static com.automation.enums.CategoryType.SMOKE;
import static com.automation.models.builders.RequestBuilder.createRequestSpecification;
import static com.automation.reports.ExtentLogger.logRequestInReport;
import static com.automation.reports.ExtentLogger.logResponseInReport;
import static io.restassured.RestAssured.given;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HeadRequestTest {

  @FrameworkAnnotation(author = USER_2, category = {SMOKE})
  @Test(description = "Validate the status code for HEAD request")
  public void headRequestToValidateStatusCode() {

    Response response = given().
      spec(createRequestSpecification()).
      when().
      head("/normal/webapi/all").
      then().
      extract().response();

    logRequestInReport(response.toString());
    logResponseInReport("API RESPONSE HEADERS", response.getHeaders().toString());
    logResponseInReport("API RESPONSE", response.prettyPrint());
    logResponseInReport("API RESPONSE STATUS CODE", String.valueOf(response.getStatusCode()));

    response.then().assertThat().statusCode(200);
  }

}
