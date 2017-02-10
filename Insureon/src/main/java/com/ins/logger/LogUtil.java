package com.ins.logger;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;


import com.ins.utils.PropsManager;

public class LogUtil {

	/**
	 * The name of the logger used for the console logger this name should be configured
	 * using log4j or some other logging mechanism 
	 */
	public static final String CUCUMBER_CONSOLE_LOG_NAME = "com.ins.log";
	/**
	 * The name of the logger used for the file logger this name should be configured
	 * using log4j or some other logging mechanism 
	 */
	public static final String CUCUMBER_FILE_LOG_NAME = "com.ins.filelog";
	
	/** The boolean property to know if we need to log to an output file */
	private static boolean LOG_XML = false;
	/** The Log for console logging */
	private static Logger log = null;
	/** The Log for file logging */
	private static Logger fileLog = null;
	
	/**
	 * Static init that will load the properties of the logging mechanism
	 */
	static {
		init();
	}

	private static void init(){
		LOG_XML = "true".equalsIgnoreCase(PropsManager.getProperty("log_xml"));
		//LOG_CONSOLE = "true".equalsIgnoreCase(PropsManager.getProperty("log_console"));
		
	    log = Logger.getLogger(CUCUMBER_CONSOLE_LOG_NAME);
	    log.debug("Logging system was initiated....");
	    if (LOG_XML){
	    	fileLog = Logger.getLogger(CUCUMBER_FILE_LOG_NAME);
	    	fileLog.debug("Logging system initiated in file....");
	    }
	}
	/**
	 * Add a blank line to the log with the default level of INFO
	 */
	public static void log(){
		log("\n", Level.INFO_INT);
	}
	
	/**
	 * Method that logs the passed message with the default level
	 * of severity (this is INFO).
	 * @param msg The final String to be logged
	 */
	public static void log(final String msg){
		log(msg, Level.INFO_INT);
	}
	
	/**
	 * Utility method that prints to console and can write to a file.
	 * @param msg The String message to log
	 * @param level The int level of severity of this message. Use 
	 * constants defined in org.apache.log4j.Level class
	 */
	public static void log(final String msg, int level){
		if (log == null)
			init();
		//log according to the passed level
		switch(level){
			case Level.FATAL_INT:
				log.fatal(msg);
				if (LOG_XML){
					fileLog.fatal(msg);
				}
				break;
			case Level.ERROR_INT:
				log.error(msg);
				if (LOG_XML){
					fileLog.error(msg);
				}
				break;
			case Level.WARN_INT:
				log.warn(msg);
				if (LOG_XML){
					fileLog.warn(msg);
				}
				break;
			case Level.INFO_INT:
				log.info(msg);
				if (LOG_XML){
					fileLog.info(msg);
				}
				break;
			case Level.DEBUG_INT:
				log.debug(msg);
				if (LOG_XML){
					fileLog.debug(msg);
				}
				break;
			case Level.TRACE_INT:
				log.trace(msg);
				if (LOG_XML){
					fileLog.trace(msg);
				}
				break;		
		}
	}
	
	/**
	 * Append the ResponseEntity response headers to the active application
	 * log
	 * @param response The ResponseEntity<String> response where to obtain 
	 * the headers from 
	 */
	public static void logResponseHeaders(ResponseEntity<String>response) {
		LogUtil.log("[RESPONSE STATUS CODE]: " + response.getStatusCode().toString());
		logHeaders(response, "RESPONSE");
	}
	
	/**
	 * Append the HttpEntity request headers to the active application
	 * log(s)
	 * @param entity The HttpEntity<String> entity where to obtain 
	 * the headers from 
	 */
	public static void logRequestHeaders(HttpEntity<String> entity) {
		logHeaders(entity, "REQUEST");
	}
	
	/**
	 * Appends the Headers of the passed entity to the active log(s) with the
	 * default log level.
	 * @param entity The HttpEntity entity where to obtain the headers from
	 * @param type The String type of entity passed to the method, normally
	 * REQUEST or RESPONSE
	 */
	public static void logHeaders(HttpEntity<String> entity, String type) {
		HttpHeaders headers = entity.getHeaders();

		Set<String> keys = headers.keySet();
		
		LogUtil.log("[" + type + " HEADERS]: ",Level.INFO_INT);
		for (String header : keys) {
			List<String> values = headers.get(header);
			StringBuilder outputValue = new StringBuilder(header).append(": ");
			if(values != null) {
				for(int i = 0; i < values.size(); i++) {
					if(i != 0) {
						outputValue.append(";");
					}
				
					outputValue.append(values.get(i));
				}
			}
			LogUtil.log(outputValue.toString());
		}
	}
	
	/**
	 * Logs the passed message with the DEBUG level of severity
	 * @param msg The String message to be logged
	 */
	public static void logDebug(String msg) {
		log(msg, Level.DEBUG_INT);
	}
	/**
	 * Logs the passed message with the ERROR level of severity
	 * @param msg The String message to be logged
	 */
	public static void logError(String msg) {
		log(msg, Level.ERROR_INT);
	}
	/**
	 * Logs the passed message with the ERROR level of severity
	 * @param msg The String message to be logged
	 */
	public static void logError(String msg, Throwable origin){
		log.error(msg,origin);
		if (LOG_XML){
			fileLog.error(msg,origin);
		}
	}
	
	/**
   * Logs the passed message with the WARN level of severity
   * prefixed by the work WARNING.
   * @param msg The String message to be logged
   */
	public static void logWarn(String msg){
	  log.warn(String.format("WARNING: %s", msg));
	  if (LOG_XML){
	    fileLog.warn(String.format("WARNING: %s", msg));
	  }
	}
	/**
	 * Logs the passed message with the TRACE level of severity
	 * @param string The String message to be logged
	 */
  public static void logTrace(String string) {
    log.trace(string);
    if (LOG_XML){
      fileLog.trace(string);
    }
  }
	
}
