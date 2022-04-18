package com.automation.listeners;

import org.testng.*;
import com.automation.reports.ExtentReportLogStatus;
import com.automation.reports.ExtentReportManager;

public class Listeners implements ITestListener, ISuiteListener {

	@Override
	public void onStart(ISuite suite) {
        System.out.println("----- ON START ------");
		ExtentReportManager.initExtentReport();
	}

	@Override
	public void onFinish(ISuite suite) {
		ExtentReportManager.flushExtentReport();
	}

	@Override
    public void onTestStart(ITestResult result) {
		ExtentReportManager.createTest(result.getMethod().getMethodName());
        ExtentReportLogStatus.pass("Test - <b>" + result.getMethod().getMethodName() + "</b>  is started");
    }

    public void onTestSuccess(ITestResult result) {
        ExtentReportLogStatus.pass("Test - <b>" + result.getMethod().getMethodName() + "</b> is passed");
    }

    public void onTestFailure(ITestResult result) {
        ExtentReportLogStatus.fail("Test - <b>" + result.getMethod().getMethodName() + "</b> is failed", result.getThrowable());
    }

    public void onTestSkipped(ITestResult result) {
        ExtentReportLogStatus.skip("Test - <b>" + result.getMethod().getMethodName() + "</b> is skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    public void onStart(ITestContext context) {

    }

	public void onFinish(ITestContext context) {

	}
}
