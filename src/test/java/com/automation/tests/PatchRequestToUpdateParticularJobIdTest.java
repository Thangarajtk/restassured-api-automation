package com.automation.tests;

import static com.automation.enums.Authors.USER_2;
import static com.automation.enums.CategoryType.SMOKE;
import static com.automation.reports.ExtentLogger.logRequest;
import static com.automation.reports.ExtentLogger.logResponse;
import static io.restassured.RestAssured.given;

import com.automation.annotations.FrameworkAnnotation;
import com.automation.base.BaseTest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PatchRequestToUpdateParticularJobIdTest extends BaseTest {

	@FrameworkAnnotation(author = USER_2, category = {SMOKE})
	@Test(description = "Validate the status code for PATCH request using Query param")
	public void patchRequestWithQueryParam() {
		
		Response response = given().
				spec(requestSpecification).
				accept(ContentType.JSON).
				queryParam("id", 4).
				queryParam("jobTitle", "Software Engineering").
				queryParam("jobDescription", "To develop web application").
			when().
				patch("/normal/webapi/update/details").
			then().
				extract().response();

		logRequest(requestSpecification);
		logResponse(response.asPrettyString());
		
		// Assert the status code
		Assert.assertEquals(response.statusCode(), 200);
	}

}
