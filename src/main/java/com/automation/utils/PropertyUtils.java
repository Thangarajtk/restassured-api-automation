package com.automation.utils;

import com.automation.constants.FrameworkConstants;
import com.automation.customexceptions.FrameworkException;
import com.automation.enums.ConfigProperties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PropertyUtils {

  private static Properties property;

  // Singleton design pattern
  public static Properties getInstance() {
    if (property == null) {
      property = new Properties();
    }
    return property;
  }

  static void loadProperties() {
    try (FileInputStream input = new FileInputStream(FrameworkConstants.CONFIG_PROPERTIES_PATH)) {
      getInstance().load(input);
    } catch (IOException e) {
      throw new FrameworkException("IOException occurred while loading Property file in the specified path");
    }
  }

  public static String getPropertyValue(ConfigProperties key) {
    loadProperties();
    if (Objects.isNull(property.getProperty(key.name().toLowerCase()))) {
      throw new FrameworkException("Property name - " + key + " is not found. Please check the config.properties");
    }
    return property.getProperty(key.name().toLowerCase());
  }
}
