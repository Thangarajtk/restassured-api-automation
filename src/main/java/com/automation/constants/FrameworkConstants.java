package com.automation.constants;

import com.automation.config.ConfigFactory;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FrameworkConstants {
	
	public static final String PROJECT_PATH = System.getProperty("user.dir");
	@Getter
	private static final String resourcesPath = PROJECT_PATH + File.separator + "src/test/resources";
	@Getter
	private static final String configPropertiesPath = resourcesPath + File.separator + "config.properties";
	@Getter
	private static final String jsonSchemaPath = resourcesPath + File.separator +"json/json-schema.json";

	private static final String extentReportPath = PROJECT_PATH + File.separator + "extent-test-report";

	public static String getExtentReportPath() {
		if (ConfigFactory.getConfig().override_reports().equalsIgnoreCase("yes")) {
			return extentReportPath + File.separator + "index.html";
		} else {
			return extentReportPath + File.separator + getCurrentDateTime() + File.separator + "index.html";
		}
	}

	private static String getCurrentDateTime() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy_MM_dd-HH_mm_ss");
		LocalDateTime localDateTime = LocalDateTime.now();
		return dateTimeFormatter.format(localDateTime);
	}
}
