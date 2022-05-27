package com.automation.tests;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import java.io.File;
import com.automation.base.BaseTest;
import com.automation.constants.FrameworkConstants;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class XmlSchemaValidation extends BaseTest {

	@Test(description = "Validating the XML DTD Schema")
	public void validateXmlDtdSchema() {
		
		File file = new File(FrameworkConstants.XML_DTD_SCHEMA_PATH);
		
		Response response = given().
				baseUri("http://localhost:9091").
			when().
				get("/student/503/score").
			then().
				statusCode(200).
				body(matchesDtd(file)).
				extract().response();
			
			logRequestInReport(stringWriter.toString());
			logResponseInReport("API RESPONSE", response.prettyPrint());
	}
	
	@Test(description = "Validating the XML XSD Schema")
	public void validateXmlXsdSchema() {
		
		File file = new File(FrameworkConstants.XML_XSD_SCHEMA_PATH);
		
		Response response = given().
				baseUri("http://localhost:9091").
			when().
				get("/send-me-file").
			then().
				log().all().
				statusCode(200).
				contentType(ContentType.XML).
				body(matchesXsd(file)).
				extract().response();
			
			logRequestInReport(stringWriter.toString());
			logResponseInReport("API RESPONSE", response.prettyPrint());
	}
}
