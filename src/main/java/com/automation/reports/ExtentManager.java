package com.automation.reports;

import com.aventstack.extentreports.ExtentTest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExtentManager {

  private static final ThreadLocal<ExtentTest> threadLocalExtentTest = new ThreadLocal<>();

  public static ExtentTest getExtentTest() {
    return threadLocalExtentTest.get();
  }

  static void setExtentTest(ExtentTest test) {
    threadLocalExtentTest.set(test);
  }

  static void unload() {
    threadLocalExtentTest.remove();
  }
}
