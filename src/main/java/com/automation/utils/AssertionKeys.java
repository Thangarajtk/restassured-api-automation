package com.automation.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AssertionKeys {

  private String jsonPath;
  private Object expectedValue;
  private Object actualValue;
  private String result;
}
