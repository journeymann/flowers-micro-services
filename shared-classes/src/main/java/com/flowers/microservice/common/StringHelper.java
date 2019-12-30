package com.flowers.microservice.common;

import java.util.Optional;
import org.slf4j.Logger;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  12/18/2019
 * @version 1.0
 *
 * Utilities for String formatting, manipulation, and queries.
 */

public class StringHelper {

	public static final String CLASSNAME = "StringHelper";	
	public static Logger LOGGER = LoggingHelper.getLogger(StringHelper.class); 

	/** This method is used to mitigate against sql injection attacks via the web service. It returns an escaped <code>String</code> 
	 * The method is used to sanitize dynamic SQL query strings by escaping the values making it much less 
	 * vulnerable to SQL injection attacks. This is a similar approach that PreparedStatement uses to mitigate injection attacks.  
	 * 
	 * @param <code>String</code> input sql query String object	    
	 * @return SQL escaped <code>String</code> which is much less vulnerable to injection attacks
	 */	

	public static String escapeSQL(String s){

		if(s == null) return s;
		if(!Optional.of(s).isPresent()) return s;

		int length = s.length();
		int newLength = length;
		// first check for characters that might
		// be dangerous and calculate a length
		// of the string that has escapes.
		for (int i=0; i<length; i++){
			char c = s.charAt(i);
			switch(c){
			case '\\':
			case '\"':
			case '\'':
			case '\0':{
				newLength += 1;
			} break;
			}
		}
		if (length == newLength){
			// nothing to escape in the string
			return s;
		}
		StringBuffer sb = new StringBuffer(newLength);
		for (int i=0; i<length; i++){
			char c = s.charAt(i);
			switch(c){
			case '\\':{
				sb.append("\\\\");
			} break;
			case '\"':{
				sb.append("\\\"");
			} break;
			case '\'':{
				sb.append("\\\'");
			} break;
			case '\0':{
				sb.append("\\0");
			} break;
			default: {
				sb.append(c);
			}
			}
		}
		return sb.toString();
	}
}
