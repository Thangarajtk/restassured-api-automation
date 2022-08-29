package com.automation.practice.wiremock;

import com.automation.base.BaseTest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.config.XmlConfig;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class XmlNamespaceValidation extends BaseTest {
	
	@Test
	public void validateXmlNamespace() {
		
		XmlConfig xml = XmlConfig.xmlConfig().declareNamespace("perctg", "https://calculate-percentage");
		
		given().
			baseUri("http://localhost:9091").
			config(RestAssured.config().xmlConfig(xml)).
		when().
			get("/student/533/score").
		then().
			log().all().
			body("student.score[0]", equalTo("300")).
			body("student.grouping[1]", equalTo("99"));
	}

}
