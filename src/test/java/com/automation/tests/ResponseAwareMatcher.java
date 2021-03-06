package com.automation.tests;

import static io.restassured.RestAssured.given;
import com.automation.base.BaseTest;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class ResponseAwareMatcher extends BaseTest {

	@Test(description="Response Aware Matcher Validation")
	public void responseAwareMatcherTest() {
		
		given().
			spec(requestSpecification).
		when().
			get("/normal/webapi/all").
		then().
			statusCode(200).
			body("jobId", response -> equalTo(response.path("jobId")));
	}
}
