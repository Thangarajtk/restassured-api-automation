package com.automation.constants;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FrameworkConstants {
	
	public static final String USERDIRECTORY = System.getProperty("user.dir");
	public static final String TESTDATAEXCELPATH = USERDIRECTORY +"/src/test/resources/TestData.xlsx";
	@Getter
	private static final String extentReportPath = USERDIRECTORY +"/ExtentReports/index.html";
	public static final String CONFIGFILEPATH = USERDIRECTORY +"/src/test/resources/config.properties";
	
	public static final String JSONSCHEMAPATH = USERDIRECTORY + "/src/test/resources/json/json-schema.json";

}
