package com.automation.reports;

import com.automation.enums.Authors;
import com.automation.enums.CategoryType;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.http.Header;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ExtentLogger {

  public static void pass(String message) {
    ExtentManager.getExtentTest().pass(MarkupHelper.createLabel(message, ExtentColor.GREEN));
  }

  public static void fail(String message, Throwable t) {
    ExtentManager.getExtentTest().fail(message).fail(t);
  }

  public static void skip(String message) {
    ExtentManager.getExtentTest().skip(message);
  }

  public static void info(Markup markup) {
    ExtentManager.getExtentTest().log(Status.INFO, markup);
  }

  // Overloaded method
  public static void info(String message) {
    ExtentManager.getExtentTest().info(message);
  }

  public static void logResponse(String response) {
    ExtentManager.getExtentTest().pass(MarkupHelper.createCodeBlock(response, CodeLanguage.JSON));
  }

  public static void logRequest(RequestSpecification requestSpecification) {
    QueryableRequestSpecification query = SpecificationQuerier.query(requestSpecification);
    ExtentManager.getExtentTest().pass(MarkupHelper.createCodeBlock(String.valueOf(query.getBody()),
                                                                    CodeLanguage.JSON));
    for (Header header : query.getHeaders()) {
      info(header.getName() + ":" + header.getValue());
    }
  }

  public static void logRequestInReport(String request) {
    ExtentLogger.info(MarkupHelper.createLabel("API REQUEST", ExtentColor.ORANGE));
    ExtentLogger.info(MarkupHelper.createCodeBlock(request));
  }

  public static void logResponseInReport(String label, String response) {
    ExtentLogger.info(MarkupHelper.createLabel(label, ExtentColor.ORANGE));
    ExtentLogger.info(MarkupHelper.createCodeBlock(response));
  }

  public static void addAuthors(Authors[] authors) {
    for (Authors author : authors) {
      ExtentManager.getExtentTest().assignAuthor(String.valueOf(author));
    }
  }

  public static void addCategories(CategoryType[] categoryTypes) {
    for (CategoryType categoryType : categoryTypes) {
      ExtentManager.getExtentTest().assignCategory(String.valueOf(categoryType));
    }
  }
}
