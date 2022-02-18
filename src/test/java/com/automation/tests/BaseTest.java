package com.automation.tests;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.output.WriterOutputStream;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import com.automation.constants.Constants;
import com.automation.reports.ExtentReportLogStatus;
import com.automation.reports.ExtentReportManager;
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
	
	private static final Logger logger = LogManager.getLogger(BaseTest.class);
	
	protected static RequestSpecification requestSpecification;
	protected static ResponseSpecification responseSpecification;
	
	public static Response response;
	
	protected StringWriter stringWriter = new StringWriter();
	protected PrintStream printStream = new PrintStream(new WriterOutputStream(stringWriter, StandardCharsets.UTF_8), true);
	
	@BeforeSuite
	public void setUpSuite() {
		logger.info("Inside @BeforeSuite Annotation to initialie the Extent Report");
		ExtentReportManager.initExtentReport();
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
			Desktop.getDesktop().browse(new File(Constants.EXTENTREPORTPATH).toURI());
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}	
	
	protected void logRequestInReport(String request) {
		ExtentReportLogStatus.info(MarkupHelper.createLabel("API REQUEST", ExtentColor.ORANGE));
		ExtentReportLogStatus.info(MarkupHelper.createCodeBlock(request));
	}
	
	protected void logResponseInReport(String label, String response) {
		ExtentReportLogStatus.info(MarkupHelper.createLabel(label, ExtentColor.ORANGE));
		ExtentReportLogStatus.info(MarkupHelper.createCodeBlock(response));
	}
}
