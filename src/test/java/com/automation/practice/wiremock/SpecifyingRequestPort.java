package com.automation.practice.wiremock;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.concurrent.TimeUnit;

import com.automation.base.BaseTest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SpecifyingRequestPort extends BaseTest {
	
	@BeforeClass
	public static void defaultConfigurationSetup() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = 9091;
	}
	
	@Test
	public void specifyPort() {
		when().
			get("/send-me-file").
		then().
			log().all().
			statusCode(200);
	}
	
	/**
	 * Wiremock service is used in this test
	 */
	@Test
	public void getRequestUsingMockService() {
		when().
			get("/mockservice").
		then().
			log().all().
			statusCode(200);
	}
	
	@Test
	public void validateResponseTime() {
		when().
			get("/mockservice").
		then().
			log().all().
			time(lessThan(1200L), TimeUnit.MILLISECONDS).
			statusCode(200);
	}
}
