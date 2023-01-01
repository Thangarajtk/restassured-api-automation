package com.automation.enums;

public enum StatusCode {

  CODE_200(200, "OK"),
  CODE_201(201, "CREATED"),
  CODE_401(401, "UNAUTHORISED"),
  CODE_404(404, "NOT FOUND");

  final int code;
  final String message;

  StatusCode(int code, String message) {
    this.code = code;
    this.message = message;
  }
}
