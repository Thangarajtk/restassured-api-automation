package com.automation.base;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.output.WriterOutputStream;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import com.automation.constants.FrameworkConstants;
import com.automation.reports.ExtentReportLogger;
import com.automation.reports.ExtentReport;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseTest {
	
	protected static RequestSpecification requestSpecification;
	protected static ResponseSpecification responseSpecification;
	
	public static Response response;
	
	protected StringWriter stringWriter = new StringWriter();
	protected PrintStream printStream = new PrintStream(new WriterOutputStream(stringWriter, StandardCharsets.UTF_8), true);
	
	@BeforeSuite
	public void setUp() {
		System.out.println("-----BEFORE SUITE-----");
		ExtentReport.initExtentReport();
	}
	
	@BeforeClass
	public void createRequestSpecification() {
		requestSpecification = new RequestSpecBuilder().
				setBaseUri("http://localhost:5050").
				addFilter(new RequestLoggingFilter(printStream)).
				build();
	}
	
	@BeforeClass
	public void createResponseSpecification() {
		responseSpecification = new ResponseSpecBuilder().
				expectStatusCode(200).
				expectContentType(ContentType.JSON).build();
	}
	
	@AfterSuite
	public void tearDown() {
		try {
			Desktop.getDesktop().browse(new File(FrameworkConstants.getExtentReportPath()).toURI());
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	protected void logRequestInReport(String request) {
		ExtentReportLogger.info(MarkupHelper.createLabel("API REQUEST", ExtentColor.ORANGE));
		ExtentReportLogger.info(MarkupHelper.createCodeBlock(request));
	}
	
	protected void logResponseInReport(String label, String response) {
		ExtentReportLogger.info(MarkupHelper.createLabel(label, ExtentColor.ORANGE));
		ExtentReportLogger.info(MarkupHelper.createCodeBlock(response));
	}
}
