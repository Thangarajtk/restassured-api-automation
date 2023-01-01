package com.automation.listeners;

import com.automation.annotations.FrameworkAnnotation;
import com.automation.reports.ExtentLogger;
import com.automation.reports.ExtentReport;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener, ISuiteListener {

  private static final String MESSAGE = "Test - <b>";

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
    ExtentLogger.addAuthors(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class)
                              .author());
    ExtentLogger.addCategories(result.getMethod().getConstructorOrMethod().getMethod().getAnnotation(FrameworkAnnotation.class)
                                 .category());
    ExtentLogger.pass(MESSAGE + result.getMethod().getMethodName() + "</b>  is started");
  }

  @Override
  public void onTestSuccess(ITestResult result) {
    ExtentLogger.pass(MESSAGE + result.getMethod().getMethodName() + "</b> is passed");
  }

  @Override
  public void onTestFailure(ITestResult result) {
    ExtentLogger.fail(MESSAGE + result.getMethod().getMethodName() + "</b> is failed", result.getThrowable());
  }

  @Override
  public void onTestSkipped(ITestResult result) {
    ExtentLogger.skip(MESSAGE + result.getMethod().getMethodName() + "</b> is skipped");
  }
}
