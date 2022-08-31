package com.automation.tests;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import java.io.File;
import com.automation.base.BaseTest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.annotations.Test;
import com.automation.constants.FrameworkConstants;
import io.restassured.response.Response;

/**
 * Dependency "json-schema-validator" has to be added to the project to perform JSON-Schema validation
 * @author Administrator
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JsonSchemaValidationTest extends BaseTest {
	
	@Test(description = "Validating the JSON Schema")
	public void validateJsonSchema() {
		
		File file = new File(FrameworkConstants.JSON_SCHEMA_PATH);
		
		Response response = given().
				spec(requestSpecification).
			when().
				get("/normal/webapi/all").
			then().
				statusCode(200).
				body(matchesJsonSchema(file)).
				extract().response();
			
			logRequestInReport(stringWriter.toString());
			logResponseInReport("API RESPONSE", response.prettyPrint());
	}
}
