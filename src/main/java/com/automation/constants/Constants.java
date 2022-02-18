package com.automation.constants;

public class Constants {
	
	// Private constructor
	private Constants() {
		
	}
	
	public static final String USERDIRECTORY = System.getProperty("user.dir");
	public static final String TESTDATAEXCELPATH = USERDIRECTORY +"/src/test/resources/TestData.xlsx";
	public static final String EXTENTREPORTPATH = USERDIRECTORY +"/ExtentReports/index.html";
	public static final String EXTENTSPARKCONFIGXMLPATH = USERDIRECTORY +"/src/test/resources/spark-html-config.xml";
	public static final String LOG4J2XMLPATH = USERDIRECTORY +"/src/test/resources/log4j2.xml";
	public static final String CONFIGFILEPATH = USERDIRECTORY +"/src/test/resources/config.properties";
	
	public static final String JSONSCHEMAPATH = USERDIRECTORY + "/src/test/resources/json/json-schema.json";

}
