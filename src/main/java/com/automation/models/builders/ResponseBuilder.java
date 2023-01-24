package com.automation.models.builders;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ResponseBuilder {

  public static ResponseSpecification createResponseSpecification() {
    return new ResponseSpecBuilder().
      expectStatusCode(200).
      expectContentType(ContentType.JSON).build();
  }
}
