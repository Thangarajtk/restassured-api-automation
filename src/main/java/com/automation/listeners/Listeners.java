package com.automation.listeners;

import org.testng.*;
import com.automation.reports.ExtentLogger;
import com.automation.reports.ExtentReport;

public class Listeners implements ITestListener, ISuiteListener {

	@Override
	public void onStart(ISuite suite) {
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

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentLogger.pass("Test - <b>" + result.getMethod().getMethodName() + "</b> is passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentLogger.fail("Test - <b>" + result.getMethod().getMethodName() + "</b> is failed", result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentLogger.skip("Test - <b>" + result.getMethod().getMethodName() + "</b> is skipped");
    }
}
