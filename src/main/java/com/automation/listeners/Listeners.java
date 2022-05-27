package com.automation.listeners;

import org.testng.*;
import com.automation.reports.ExtentLogger;
import com.automation.reports.ExtentReport;

public class Listeners implements ITestListener, ISuiteListener {

	@Override
	public void onStart(ISuite suite) {
        System.out.println("----- ON START ------");
		ExtentReport.initExtentReport();
	}

	@Override
	public void onFinish(ISuite suite) {
		ExtentReport.flushExtentReport();
	}

	@Override
    public void onTestStart(ITestResult result) {
		ExtentReport.createTest(result.getMethod().getMethodName());
        ExtentLogger.pass("Test - <b>" + result.getMethod().getMethodName() + "</b>  is started");
    }

    public void onTestSuccess(ITestResult result) {
        ExtentLogger.pass("Test - <b>" + result.getMethod().getMethodName() + "</b> is passed");
    }

    public void onTestFailure(ITestResult result) {
        ExtentLogger.fail("Test - <b>" + result.getMethod().getMethodName() + "</b> is failed", result.getThrowable());
    }

    public void onTestSkipped(ITestResult result) {
        ExtentLogger.skip("Test - <b>" + result.getMethod().getMethodName() + "</b> is skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public void onStart(ITestContext context) {

    }

	public void onFinish(ITestContext context) {

	}
}
