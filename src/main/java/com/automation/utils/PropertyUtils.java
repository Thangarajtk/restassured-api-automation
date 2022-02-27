package com.automation.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.automation.constants.FrameworkConstants;


public class PropertyUtils {
	
	private PropertyUtils() {}
	
	protected static Properties prop;
	static FileInputStream input;
	
	public static void initProperty() throws IOException {
		prop = new Properties();
		try {
			input = new FileInputStream(FrameworkConstants.CONFIGFILEPATH);
			prop.load(input);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			input.close();
		}
	}
	
	public static String getPropertyValue(String key) {
		String propertyValue = null;
		try {
			initProperty();
			propertyValue = prop.getProperty(key);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return propertyValue;
	}
}
