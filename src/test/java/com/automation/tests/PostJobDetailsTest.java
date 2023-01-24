package com.automation.tests;

import com.automation.annotations.FrameworkAnnotation;
import com.automation.constants.FrameworkConstants;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.annotations.Test;

import java.io.File;

import static com.automation.enums.Authors.USER_2;
import static com.automation.enums.CategoryType.SMOKE;
import static com.automation.models.builders.RequestBuilder.createRequestSpecification;
import static com.automation.reports.ExtentLogger.logRequest;
import static com.automation.reports.ExtentLogger.logResponse;
import static io.restassured.RestAssured.given;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PostJobDetailsTest {

  @FrameworkAnnotation(author = USER_2, category = {SMOKE})
  @Test(enabled = false, description = "Validate the status code for POST request")
  public void postRequestUsingJsonBody() {

    File file = new File(FrameworkConstants.RESOURCES_FOLDER_PATH + "/json/create_job_details.json");

    RequestSpecification requestSpecification = createRequestSpecification();
    Response response = given().
      spec(createRequestSpecification()).
      headers("Content-Type", "application/json", "Accept", "application/json").
      body(file).
      when().
      post("/normal/webapi/add").
      then().
      extract().response();

    logRequest(requestSpecification);
    logResponse(response.asPrettyString());

    response.then().assertThat().statusCode(201);
  }
}
