package org.mapjdbc.common;

/**
 * 
 * Description: In PropertyReader class, the functions are defined to store the Properties in the Property file as well as 
 *              load the PropertyFile which can be passed to the calling function to access any property stored in this file.
 */
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.mapjdbc.exceptions.ApplicationException;

public class PropertyReader {
	
	/* 
	 * This function is used to load the mapjdbcs.properties file and is returned to the calling function, where the calling function
	 * can use any of the properties stored in it
	 *     
	 */
	public static Properties getProperties(){
			Properties prop = new Properties();
	    	try {
               //load a properties file
    	    		prop.load(new FileReader("mapjdbcs.properties"));
	    	} catch (IOException ex) {
    		    ex.printStackTrace();
            }
 	return prop;
	}
	
	/* 
	 * This function is used to store the properties into the mapjdbcs.properties file 
	 *     
	 */
	public static void setProperties(Map<String,String> property,String comments) throws ApplicationException{
		Properties prop = new Properties();
    	FileWriter writer = null;
    	//to set the properties value
    	Set<String> key = property.keySet();
    	for(String Key : key){
    	prop.setProperty(Key, property.get(Key));
    	}
    	try {
    		writer = new FileWriter("mapjdbcs.properties",true);
			prop.store(writer, comments);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new ApplicationException(102,
					"Cannot find the mapjdbcs.properties file to store the properties");
		}
	}
	
	

}
