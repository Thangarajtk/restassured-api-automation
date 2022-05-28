package com.automation.constants;

import com.automation.config.ConfigFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FrameworkConstants {

    private static final String PROJECT_PATH = System.getProperty("user.dir");
    private static final String RESOURCES_FOLDER_PATH = PROJECT_PATH + File.separator + "src" + File.separator
            + "test" + File.separator + "resources";
    public static final String CONFIG_PROPERTIES_PATH = RESOURCES_FOLDER_PATH + File.separator + "config.properties";
    public static final String JSON_SCHEMA_PATH = RESOURCES_FOLDER_PATH + File.separator + "json" + File.separator + "json-schema.json";
    public static final String XML_XSD_SCHEMA_PATH = RESOURCES_FOLDER_PATH + File.separator + "xml" + File.separator + "xml-xsd-schema.xsd";
    public static final String XML_DTD_SCHEMA_PATH = RESOURCES_FOLDER_PATH + File.separator + "xml" + File.separator + "xml-dtd-schema.dtd";

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
