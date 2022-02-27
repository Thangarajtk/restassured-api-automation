package com.automation.reports;

import com.automation.config.ConfigFactory;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExtentReportLogStatus {

    public static void pass(String message) {
        ExtentReportManager.getExtentTest().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
    }

    public static void fail(String message, Throwable t) {
        ExtentReportManager.getExtentTest().fail(message).fail(t);

    }

    public static void skip(String message) {
        ExtentReportManager.getExtentTest().log(Status.SKIP, message);
    }

    public static void info(Markup markup) {
        ExtentReportManager.getExtentTest().log(Status.INFO, markup);
    }

    // Overloaded method
    public static void info(String message) {
        ExtentReportManager.getExtentTest().log(Status.INFO, message);
    }

    public static void warning(String message) {
        ExtentReportManager.getExtentTest().log(Status.WARNING, message);
    }
}
