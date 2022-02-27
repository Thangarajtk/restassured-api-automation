package com.automation.listeners;

import com.automation.config.ConfigFactory;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryFailedTests implements IRetryAnalyzer {

	private int count = 0;
	private final int maxRetry = ConfigFactory.getConfig().retry_count();

	@Override
	public boolean retry(ITestResult result) {
		boolean value = false;
		if (ConfigFactory.getConfig().retry_failed_tests()) {
			value = count < maxRetry;
			count++;
		}
		return value;
	}
}