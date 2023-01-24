package com.automation.tests;

import com.automation.annotations.FrameworkAnnotation;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.automation.enums.Authors.USER_1;
import static com.automation.enums.CategoryType.SMOKE;
import static com.automation.models.builders.RequestBuilder.createRequestSpecification;
import static com.automation.reports.ExtentLogger.logRequest;
import static com.automation.reports.ExtentLogger.logResponse;
import static io.restassured.RestAssured.given;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DeleteParticularJobIdTest {

  @FrameworkAnnotation(author = USER_1, category = {SMOKE})
  @Test(description = "Validate the status code for DELETE request")
  public void deleteSpecificRecordUsingDeleteRequest() {

    RequestSpecification requestSpecification = createRequestSpecification();
    Response response = given().
      spec(requestSpecification).
      accept(ContentType.JSON).
      contentType(ContentType.JSON).
      when().
      delete("/normal/webapi/remove/3").
      then().
      extract().response();

    logRequest(requestSpecification);
    logResponse(response.asPrettyString());

    // Assert the status code
    Assert.assertEquals(response.statusCode(), 200);
  }

  @FrameworkAnnotation(author = USER_1, category = {SMOKE})
  @Test(description = "Validate the DELETE request with Path param")
  public void deleteRequestWithPathParam() {
    RequestSpecification requestSpecification = createRequestSpecification();
    Response response = given().
      spec(requestSpecification).
      accept(ContentType.JSON).
      contentType(ContentType.JSON).
      pathParam("id", 5).
      when().
      delete("/normal/webapi/remove/{id}").
      then().
      extract().response();

    logRequest(requestSpecification);
    logResponse(response.asPrettyString());

    // Assert the status code
    Assert.assertEquals(response.statusCode(), 200);
  }
}
