package com.automation.models.builders;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import static java.net.HttpURLConnection.HTTP_OK;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ResponseBuilder {

  public static ResponseSpecification createResponseSpecification() {
    return new ResponseSpecBuilder().
      expectStatusCode(HTTP_OK).
      expectContentType(ContentType.JSON).build();
  }
}
