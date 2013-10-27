package org.mapjdbc.common;

/**
 * 
 * Description: In PropertyReader class, the functions are defined to store the Properties in the Property file as well as 
 *              load the PropertyFile which can be passed to the calling function to access any property stored in this file.
 */
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.mapjdbc.exceptions.ApplicationException;

public class PropertyReader {

	/**
	 * It would be easier to just load the properties file at the begining of
	 * the project into a hashmap and use is like this. Since this would be at
	 * the beginging of the project you can catch an exception and print it
	 * here.
	 * 
	 * */

	public PropertyReader instance;
	public static Map<String, String> propertyMap;
	public static Properties properties;

	private PropertyReader() {
		// We dont want any more copies of this class.
	}


	static {
		properties = new Properties();
		try {
			properties.load(new FileReader("mapjdbcs.properties"));
			propertyMap = new HashMap<String, String>();
			System.out.println("Properties file loaded");
			for (String name : properties.stringPropertyNames()) {
				propertyMap.put(name, properties.getProperty(name));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * This function is used to load the mapjdbcs.properties file and is
	 * returned to the calling function, where the calling function can use any
	 * of the properties stored in it
	 */
	public static Properties getProperties() {
		Properties prop = new Properties();
		try {
			// load a properties file
			prop.load(new FileReader("mapjdbcs.properties"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop;
	}

	/**
	 * This function is used to store the properties into the
	 * mapjdbcs.properties file.
	 * 
	 * @taran: No need of this function
	 */
	public static void setProperties(Map<String, String> property,
			String comments) throws ApplicationException {
		Properties prop = new Properties();
		FileWriter writer = null;
		// to set the properties value
		Set<String> key = property.keySet();
		for (String Key : key) {
			prop.setProperty(Key, property.get(Key));
		}
		try {
			writer = new FileWriter("mapjdbcs.properties", true);
			prop.store(writer, comments);
		} catch (IOException e) {
			throw new ApplicationException(102,
					"Cannot find the mapjdbcs.properties file to store the properties");
		}
	}

	public static Map<String, String> getPropertyMap() {
		return propertyMap;
	}

	public static void setPropertyMap(Map<String, String> propertyMap) {
		PropertyReader.propertyMap = propertyMap;
	}

	public static void main(String[] args) {
		PropertyReader propertyReader;
	}

}
