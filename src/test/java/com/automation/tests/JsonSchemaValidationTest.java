package com.automation.tests;

import com.automation.annotations.FrameworkAnnotation;
import com.automation.constants.FrameworkConstants;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.annotations.Test;

import java.io.File;

import static com.automation.enums.Authors.USER_2;
import static com.automation.enums.CategoryType.SMOKE;
import static com.automation.models.builders.RequestBuilder.createRequestSpecification;
import static com.automation.reports.ExtentLogger.logRequestInReport;
import static com.automation.reports.ExtentLogger.logResponseInReport;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

/**
 * Dependency "json-schema-validator" has to be added to the project to perform JSON-Schema validation
 *
 * @author Administrator
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonSchemaValidationTest {

  @FrameworkAnnotation(author = USER_2, category = {SMOKE})
  @Test(description = "Validating the JSON Schema")
  public void validateJsonSchema() {

    File file = new File(FrameworkConstants.JSON_SCHEMA_PATH);

    Response response = given().
      spec(createRequestSpecification()).
      when().
      get("/normal/webapi/all").
      then().
      statusCode(200).
      body(matchesJsonSchema(file)).
      extract().response();

    logRequestInReport(response.toString());
    logResponseInReport("API RESPONSE", response.prettyPrint());
  }
}
