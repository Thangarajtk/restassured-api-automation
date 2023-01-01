package com.automation.utils;

import com.automation.constants.FrameworkConstants;
import com.automation.enums.ConfigProperties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PropertyHelper {

  private static final Properties properties = new Properties();
  private static final Map<String, String> MAP = new HashMap<>();

  static {
    try (FileInputStream inputStream = new FileInputStream(FrameworkConstants.CONFIG_PROPERTIES_PATH)) {
      properties.load(inputStream);
    } catch (IOException e) {
      System.exit(0);
    }
  }

  public static String getValue(ConfigProperties key) {
    return MAP.get(key.name().toLowerCase());
  }
}
