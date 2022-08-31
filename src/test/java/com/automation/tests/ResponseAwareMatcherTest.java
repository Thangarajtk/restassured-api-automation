package com.automation.tests;

import static io.restassured.RestAssured.given;
import com.automation.base.BaseTest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ResponseAwareMatcherTest extends BaseTest {

	@Test(description = "Response Aware Matcher Validation")
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
