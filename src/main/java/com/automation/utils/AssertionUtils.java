package com.automation.utils;

import com.automation.reports.ExtentLogger;
import com.automation.reports.ExtentManager;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class AssertionUtils {

  public static void assertExpectedValuesWithJsonPath(Response response,
                                                      Map<String, Object> expectedValuesMap) {

    List<AssertionKeys> actualValueMap = new ArrayList<>();
    actualValueMap.add(new AssertionKeys("JSON_PATH", "EXPECTED_VALUE", "ACTUAL_VALUE", "RESULT"));
    boolean allMatch = true;

    Set<String> jsonPaths = expectedValuesMap.keySet();
    for(String jsonPath : jsonPaths) {
      Optional<Object> actualValue = Optional.ofNullable(response.jsonPath().get(jsonPath));
      if (actualValue.isPresent()) {
        Object value = actualValue.get();
        if (value.equals(expectedValuesMap.get(jsonPath))) {
          actualValueMap.add(new AssertionKeys(jsonPath, expectedValuesMap.get(jsonPath), value, "MATCHED"));
        }
        else {
          allMatch = false;
          actualValueMap.add(new AssertionKeys(jsonPath, expectedValuesMap.get(jsonPath), value, "NOT_MATCHED"));
        }
      }
      else {
        actualValueMap.add(new AssertionKeys(jsonPath, expectedValuesMap.get(jsonPath), "VALUE_NOT_FOUND", "MATCHED"));
      }
    }
    if (allMatch)
      ExtentLogger.pass("All assertions are passed");
    else
      ExtentLogger.logFailureDetails("All assertions are not passed");

    String[][] finalAssertionMap = actualValueMap.stream().map(assertions -> new String[] {assertions.getJsonPath(),
        String.valueOf(assertions.getExpectedValue()), String.valueOf(assertions.getActualValue()), assertions.getResult()})
      .toArray(String[][] :: new);
    ExtentManager.getExtentTest().info(MarkupHelper.createTable(finalAssertionMap));
  }
}
