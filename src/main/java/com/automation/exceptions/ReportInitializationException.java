package com.automation.exceptions;

public class ReportInitializationException extends FrameworkException {

  /**
   * Pass the message that needs to be appended to the stacktrace
   *
   * @param message Details about the exception or custom message
   */
  public ReportInitializationException(String message) {
    super(message);
  }

  /**
   * @param message Details about the exception or custom message
   * @param cause   Pass the enriched stacktrace or customised stacktrace
   */
  public ReportInitializationException(String message, Throwable cause) {
    super(message, cause);
  }

}
