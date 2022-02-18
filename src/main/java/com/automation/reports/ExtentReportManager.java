package com.automation.reports;

import java.io.File;
import java.net.InetAddress;

import com.automation.constants.Constants;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportManager {
	
	private ExtentReportManager() {}

	private static ExtentReports extentReport;
	
	private static ExtentSparkReporter htmlReporter = new ExtentSparkReporter(Constants.EXTENTREPORTPATH);
	private static final File sparkConfigXmlPath = new File(Constants.EXTENTSPARKCONFIGXMLPATH);
	
	private static final ThreadLocal<ExtentTest> threadLocalExtentTest = new ThreadLocal<ExtentTest>();

	static InetAddress ip;
	static String hostname;


	public static void initExtentReport() {
		try {
			setExtentReport(new ExtentReports());
			getExtentReport().attachReporter(htmlReporter);

			ip = InetAddress.getLocalHost();
			hostname = ip.getHostName();
			getExtentReport().setSystemInfo("OS", System.getProperty("os.name"));
			getExtentReport().setSystemInfo("Host Name", hostname);
			getExtentReport().setSystemInfo("Environment", "Automation Testing");
			getExtentReport().setSystemInfo("User Name", System.getProperty("user.name"));
		
			htmlReporter.loadXMLConfig(sparkConfigXmlPath);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static ExtentTest getExtentTest() {
		return threadLocalExtentTest.get();
	}

	public static void setExtentTest(ExtentTest test) {
		threadLocalExtentTest.set(test);
	}
	
	public static void unload() {
		threadLocalExtentTest.remove();
	  }

	public static ExtentReports getExtentReport() {
		return extentReport;
	}

	public static void setExtentReport(ExtentReports extent) {
		ExtentReportManager.extentReport = extent;
	}

}
