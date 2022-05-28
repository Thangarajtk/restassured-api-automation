package com.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import com.automation.constants.FrameworkConstants;
import com.automation.customexceptions.FrameworkException;
import com.automation.enums.ConfigProperties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PropertyUtils {

	private static Properties property;

	// Singleton design pattern
	public static Properties getInstance() {
		if (property == null) {
			property = new Properties();
		}
		return property;
	}

	static void loadProperties(String propertyFilePath) {
		try (FileInputStream input = new FileInputStream(propertyFilePath)) {
			getInstance().load(input);
		} catch (IOException e) {
			throw new FrameworkException("IOException occurred while loading Property file in the specified path");
		}
	}

	public static String getPropertyValue(ConfigProperties key) {
		loadProperties(FrameworkConstants.CONFIG_PROPERTIES_PATH);
		if (Objects.isNull(property.getProperty(key.name().toLowerCase())) ||
				Objects.isNull(key.name().toLowerCase())) {
			throw new FrameworkException("Property name - " + key + " is not found. Please check the config.properties");
		}
		return property.getProperty(key.name().toLowerCase());
	}
}
