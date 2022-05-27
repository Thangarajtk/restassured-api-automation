package com.automation.base;

import com.automation.reports.ExtentLogger;
import com.automation.reports.ExtentReport;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.commons.io.output.WriterOutputStream;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import java.io.PrintStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;

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

    /**
     * RequestSpecification is an Interface and RequestSpecBuilder is a class
     */
    @BeforeClass
    public void createRequestSpecification() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://localhost:5050")
                .addFilter(new RequestLoggingFilter(printStream))
                .log(LogDetail.ALL)
                .build();
    }

    @BeforeClass
    public void createResponseSpecification() {
        responseSpecification = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).build();
    }

    @AfterSuite
    public void tearDown() {

    }

    protected void logRequestInReport(String request) {
        ExtentLogger.info(MarkupHelper.createLabel("API REQUEST", ExtentColor.ORANGE));
        ExtentLogger.info(MarkupHelper.createCodeBlock(request));
    }

    protected void logResponseInReport(String label, String response) {
        ExtentLogger.info(MarkupHelper.createLabel(label, ExtentColor.ORANGE));
        ExtentLogger.info(MarkupHelper.createCodeBlock(response));
    }
}
