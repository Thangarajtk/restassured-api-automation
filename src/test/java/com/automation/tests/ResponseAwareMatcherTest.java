package com.automation.tests;

import com.automation.annotations.FrameworkAnnotation;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.annotations.Test;

import static com.automation.enums.Authors.USER_2;
import static com.automation.enums.CategoryType.SMOKE;
import static com.automation.models.builders.RequestBuilder.createRequestSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ResponseAwareMatcherTest {

  @FrameworkAnnotation(author = USER_2, category = {SMOKE})
  @Test(description = "Response Aware Matcher Validation")
  public void responseAwareMatcherTest() {

    given().
      spec(createRequestSpecification()).
      when().
      get("/normal/webapi/all").
      then().
      statusCode(200).
      body("jobId", response -> equalTo(response.path("jobId")));
  }
}
