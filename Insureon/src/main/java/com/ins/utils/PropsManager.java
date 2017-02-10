package com.ins.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ins.logger.LogUtil;



/**
 * This class holds the mechanism to use all the Properties that loads 
 * before the tests.
 * 
 *
 */
public class PropsManager {
	
	private static Log log = LogFactory.getLog(PropsManager.class);

	static Properties props;
	static String sessionIdName = null;
	
	static {		
		props = new Properties();		
	}
	/**
	 * This method checks if the passed property name exist within the
	 * loaded system properties and compares its value (ignoring casing)
	 * @param name String name of the property to lookup
	 * @return true if property exist present and value equals passed value,
	 * false otherwise
	 */
	public static boolean propertyHasValue(String name, String value){
	  try{
	    String prop = getProperty(name);
	    return StringUtils.equalsIgnoreCase(prop, value);
	  }catch(Throwable t){
	    LogUtil.logWarn(String.format("Unable to retrieve property %s to check value",name));
	  }
	  return false;
	}
	
	public static String getProperty(String key){
		return props.getProperty(key);
	}

	public static String printProperties() {
		
		StringBuffer buff = new StringBuffer();
		Iterator<Object> k = props.keySet().iterator();
		while (k.hasNext()){
			String key = (String) k.next();
			buff.append("[" + key + "=" + props.getProperty(key) + "] ");
		}
		
		return buff.toString();
	}
	
	

	public static String getErrorMessage(String type) {
		String message = getProperty(type);
		return message;
	}
	
	


	public static synchronized InputStream tryToLoadFromEverywhere(String filename){
		InputStream result = null;
		result = System.class.getResourceAsStream(filename);
		if (result != null)
			return result;
		
		result = Thread.currentThread().getContextClassLoader().getResourceAsStream(filename);
		if (result != null)
			return result;
		
		result = Thread.currentThread().getClass().getResourceAsStream(filename);
		if (result != null)
			return result;
		
		result = ClassLoader.getSystemResourceAsStream(filename);
		if (result != null)
			return result;
		
		result = ClassLoader.getSystemClassLoader().getResourceAsStream(filename);
		if (result != null)
			return result;
		
		//Try to load the static environment.properties from pom.xml directory
		try{
			result = new FileInputStream(filename);
		}catch (FileNotFoundException e) {
			//Try to load with the complete file path name
			String path = getInputDataFileNameFromUserDir(filename);
			try {
				result = new FileInputStream(path);
			} catch (FileNotFoundException e1) {
				LogUtil.log(String.format("Unable to load %s file. Tried all locations. Last tried: %s",filename,path));
			}
		}
		
		if (result == null)
			LogUtil.log(String.format("Unable to load %s file. Tried all locations.",filename));
		return result;
	}
	
	/**
	 * This method returns the absolute file name passed as a parameter based on the
	 * location of the user.dir System property. For example if 
	 * user.dir = /opt/jenkins/workspace and fileName = inputData/user.properties
	 * the result will be: /opt/jenkins/workspace/inputData/user.properties
	 * 
	 * Note: File separator will depend on the file.separator System property
	 * @param fileName The name of the file name to locate
	 * @return The complete (absolute) path of the fileName
	 */
	public static synchronized String getInputDataFileNameFromUserDir(String fileName){
		String path = new StringBuilder().append(System.getProperty("user.dir"))
				.append(System.getProperty("file.separator")).append(fileName)
				.toString();
		return path.toString();
	}

	/**
	 * This method returns a boolean value to know if the proxy settings
	 * are enabled to be used in the cucumber tests suite. It checks the
	 * loaded settings from proxy.properties file to see if the proxy_enable
	 * setting is set to true.
	 * 
	 * @return boolean value to know if the proxy is enabled or not (true,false)
	 */
	public static boolean isProxyEnabled() {
		Boolean b = Boolean.valueOf(getProperty("proxy_enabled"));
		return b.booleanValue();
	}

	/**
	 * Creates a new Properties object with the information loaded
	 * from the propsName file name. It uses the function 
	 * tryToLoadFromEverywhere and loads the content of the returned
	 * inputStream to a new Properties object.
	 * @param propsName The name of the Properties file to load
	 * @return A new Properties file with the information loaded.
	 * @throws IOException In case error while loading properties happen.
	 */
	public static Properties loadPropertiesFile(String propsName) throws IOException{
	  InputStream input = tryToLoadFromEverywhere(propsName);
	  Properties props = new Properties();
    props.load(input);
	  return props;
	}
	
}
