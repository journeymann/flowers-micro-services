package com.flowers.microservice.common;

import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  12/18/2019
 * @version 1.0
 *
 */
public class LoggingHelper {
	
	/** This returns a logger object based on the class/type parameter passed
	 * @param <code>Class</code> the class to associate with appender
	 * @return <code>Logger</code> logger class to be used for logging 
	 */	
	public static Logger getLogger(Class<?> klass){

		return (Logger)LogManager.getLogger(klass);
	}
}



