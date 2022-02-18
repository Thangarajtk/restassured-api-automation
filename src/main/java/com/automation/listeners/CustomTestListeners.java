package com.automation.listeners;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.automation.reports.ExtentReportLogStatus;
import com.automation.reports.ExtentReportManager;

public class CustomTestListeners implements ITestListener{
	
	private static final Logger logger = LogManager.getLogger(CustomTestListeners.class);
	
	private String testCaseName; // This variable can be accessed through getters and setters method

	public String getTestcaseName() {
		return testCaseName;
	}

	public void setTestcaseName(String testcaseName) {
		testCaseName = testcaseName;
	}
	
	public void onTestStart(ITestResult result) {
		testCaseName = result.getName();
		setTestcaseName(testCaseName);
		ExtentReportManager.setExtentTest(ExtentReportManager.getExtentReport().createTest(testCaseName)
				.assignAuthor("REST API Automation by " + System.getProperty("user.name")));
		ExtentReportLogStatus.pass(testCaseName + " is started successfully");
		logger.info(testCaseName + " is started successfully");
	}

	public void onTestSuccess(ITestResult result)
	{
		ExtentReportLogStatus.pass(result.getMethod().getDescription() + " test case is passed");
		logger.info(result.getName() + " test case is passed");
	}

	public void onTestFailure(ITestResult result)
	{
		ExtentReportLogStatus.fail(result.getMethod().getDescription() + " is failed");
		ExtentReportLogStatus.fail(result.getThrowable().toString());
		logger.error(result.getName() + " test case is failed");
	}

	public void onTestSkipped(ITestResult result)
	{
		ExtentReportLogStatus.skip(result.getName()+ " is failed");
	}

	public void onFinish(ITestContext testcontext)
	{
		ExtentReportManager.getExtentReport().flush();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
		
	}

	public void onStart(ITestContext context) {
		
		
	}
}
