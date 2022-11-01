package com.automation.tests;

import static com.automation.enums.Authors.USER_2;
import static com.automation.enums.CategoryType.SMOKE;
import static com.automation.reports.ExtentLogger.logRequest;
import static com.automation.reports.ExtentLogger.logResponse;
import static io.restassured.RestAssured.*;
import java.io.File;

import com.automation.annotations.FrameworkAnnotation;
import com.automation.base.BaseTest;
import com.automation.constants.FrameworkConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.annotations.Test;
import io.restassured.response.Response;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PostJobDetailsTest extends BaseTest {

	@FrameworkAnnotation(author = USER_2, category = {SMOKE})
	@Test(enabled = false, description = "Validate the status code for POST request")
	public void postRequestUsingJsonBody() {
		
		File file = new File(FrameworkConstants.RESOURCES_FOLDER_PATH + "/json/create_job_details.json");
		
		Response response = given().
			spec(requestSpecification).
			headers("Content-Type", "application/json", "Accept", "application/json").
			body(file).
		when().
			post("/normal/webapi/add").
		then().
			extract().response();

		logRequest(requestSpecification);
		logResponse(response.asPrettyString());
		
		response.then().assertThat().statusCode(201);
	}
}
