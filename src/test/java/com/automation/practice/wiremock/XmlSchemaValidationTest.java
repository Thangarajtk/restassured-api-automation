package com.automation.practice.wiremock;

import com.automation.constants.FrameworkConstants;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.annotations.Test;

import java.io.File;

import static com.automation.reports.ExtentLogger.logRequestInReport;
import static com.automation.reports.ExtentLogger.logResponseInReport;
import static io.restassured.RestAssured.given;
import static io.restassured.matcher.RestAssuredMatchers.matchesDtd;
import static io.restassured.matcher.RestAssuredMatchers.matchesXsd;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class XmlSchemaValidationTest {

  @Test(description = "Validating the XML DTD Schema")
  public void validateXmlDtdSchema() {

    File file = new File(FrameworkConstants.XML_DTD_SCHEMA_PATH);

    Response response = given().
      baseUri("http://localhost:9091").
      when().
      get("/student/503/score").
      then().
      statusCode(200).
      body(matchesDtd(file)).
      extract().response();

    logRequestInReport(response.toString());
    logResponseInReport("API RESPONSE", response.prettyPrint());
  }

  @Test(description = "Validating the XML XSD Schema")
  public void validateXmlXsdSchema() {

    File file = new File(FrameworkConstants.XML_XSD_SCHEMA_PATH);

    Response response = given().
      baseUri("http://localhost:9091").
      when().
      get("/send-me-file").
      then().
      log().all().
      statusCode(200).
      contentType(ContentType.XML).
      body(matchesXsd(file)).
      extract().response();

    logRequestInReport(response.toString());
    logResponseInReport("API RESPONSE", response.prettyPrint());
  }
}
