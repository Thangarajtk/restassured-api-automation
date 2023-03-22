package com.automation.tests;

import com.automation.annotations.FrameworkAnnotation;
import com.automation.constants.FrameworkConstants;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.io.File;

import static com.automation.enums.Authors.USER_2;
import static com.automation.enums.CategoryType.SMOKE;
import static com.automation.models.builders.RequestBuilder.createRequestSpecification;
import static com.automation.reports.ExtentLogger.logRequest;
import static com.automation.reports.ExtentLogger.logResponse;
import static io.restassured.RestAssured.given;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PutRequestToUpdateJobDetailsTest {

  @FrameworkAnnotation(author = USER_2, category = {SMOKE})
  @Test(description = "Validate the status code for PUT request")
  public void updateUsingPutRequest() {

    File file = new File(FrameworkConstants.RESOURCES_FOLDER_PATH + "/json/update_job_details.json");
    RequestSpecification requestSpecification = createRequestSpecification();
    Response response = given().
      spec(createRequestSpecification()).
      headers("Content-Type", "application/json", "Accept", "application/json").
      body(file).
      when().
      put("/normal/webapi/update").
      then().
      extract().response();

    logRequest(requestSpecification);
    logResponse(response.asPrettyString());

    Assertions.assertThat(response.statusCode()).isEqualTo(200);
  }
}
