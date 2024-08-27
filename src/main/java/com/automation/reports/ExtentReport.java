package com.automation.reports;

import com.automation.exceptions.ReportInitializationException;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Objects;

import static com.automation.constants.FrameworkConstants.getExtentReportPath;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExtentReport {

  private static final ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(getExtentReportPath());
  private static ExtentReports extentReports;

  /**
   * This method is to initialize the Extent Report
   */
  public static void initExtentReport() {
    try {
      if (Objects.isNull(extentReports)) {
        extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        InetAddress ip = InetAddress.getLocalHost();
        String hostname = ip.getHostName();
        extentReports.setSystemInfo("Host Name", hostname);
        extentReports.setSystemInfo("Environment", "API Automation - RestAssured");
        extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
        extentSparkReporter.config().setDocumentTitle("HTML Report");
        extentSparkReporter.config().setReportName("API Automation Test");
        extentSparkReporter.config().setTheme(Theme.DARK);
      }
    } catch (Exception e) {
      throw new ReportInitializationException("Failed to initialize extent report - " + e.getMessage());
    }
  }

  public static void createTest(String testCaseName) {
    ExtentManager.setExtentTest(extentReports.createTest(testCaseName));
  }

  public static void flushExtentReport() {
    if (Objects.nonNull(extentReports)) {
      extentReports.flush();
    }
    ExtentManager.unload();
    try {
      Desktop.getDesktop().browse(new File(getExtentReportPath()).toURI());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
