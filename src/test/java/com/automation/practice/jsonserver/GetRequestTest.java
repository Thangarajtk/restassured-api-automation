package com.automation.practice.jsonserver;

import com.automation.models.builders.RequestBuilder;
import com.automation.reports.ExtentLogger;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.annotations.Test;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GetRequestTest {

  @Test
  public void getEmployeeDetails() {

    RequestSpecification requestSpecification = RequestBuilder.buildRequest()
      .pathParam("id", 13);

    Response response = requestSpecification.get("/employees/{id}");

    ExtentLogger.logResponse(response.asPrettyString());
  }
}
