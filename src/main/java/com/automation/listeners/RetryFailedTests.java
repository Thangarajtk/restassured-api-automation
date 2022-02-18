package com.automation.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import com.automation.reports.ExtentReportLogStatus;

public class RetryFailedTests implements IRetryAnalyzer{

	private int retryCount = 0;
	private int maxRetryCount = 0;
	
	public boolean retry(ITestResult result) {
		if (!result.isSuccess()) {
			if (retryCount < maxRetryCount) {
				retryCount++;
				result.setStatus(ITestResult.FAILURE);
				ExtentReportLogStatus.fail(result.getMethod() + " failed");
				return true;
			}
		} else {
			result.setStatus(ITestResult.SUCCESS);
		}
		return false;
	}

}
