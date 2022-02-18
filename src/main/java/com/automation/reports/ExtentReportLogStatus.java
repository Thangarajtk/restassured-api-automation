package com.automation.reports;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.Markup;

public class ExtentReportLogStatus {
	
	private ExtentReportLogStatus() {}

	public static void pass(String message)
	{
		ExtentReportManager.getExtentTest().log(Status.PASS, message);
	}

	public static void fail(String message)
	{
		ExtentReportManager.getExtentTest().log(Status.FAIL, message);
	}

	public static void info(Markup markup)
	{
		ExtentReportManager.getExtentTest().log(Status.INFO, markup);
	}
	
	// Overloaded method
	public static void info(String message)
	{
		ExtentReportManager.getExtentTest().log(Status.INFO, message);
	}

	public static void skip(String message)
	{
		ExtentReportManager.getExtentTest().log(Status.SKIP, message);
	}

	public static void warning(String message)
	{
		ExtentReportManager.getExtentTest().log(Status.WARNING, message);
	}
}
