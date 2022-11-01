package com.automation.tests;

import static com.automation.enums.Authors.USER_2;
import static com.automation.enums.CategoryType.SMOKE;
import static io.restassured.RestAssured.given;

import com.automation.annotations.FrameworkAnnotation;
import com.automation.base.BaseTest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.annotations.Test;
import io.restassured.response.Response;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class HeadRequestTest extends BaseTest {

	@FrameworkAnnotation(author = USER_2, category = {SMOKE})
	@Test(description = "Validate the status code for HEAD request")
	public void headRequestToValidateStatusCode() {
		
		Response response = given().
			spec(requestSpecification).
		when().
			head("/normal/webapi/all").
		then().
			extract().response();
		
		logRequestInReport(stringWriter.toString());
		logResponseInReport("API RESPONSE HEADERS", response.getHeaders().toString());
		logResponseInReport("API RESPONSE", response.prettyPrint());
		logResponseInReport("API RESPONSE STATUS CODE", String.valueOf(response.getStatusCode()));
			
		response.then().assertThat().statusCode(200);
	}

}
