/**
 * 
 */
package com.flowers.microservice.logging.loggly;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

public interface Log {
	
	Integer OFF=0;
	Integer FATAL	=100;
	Integer ERROR	=200;
	Integer WARN	=300;
	Integer INFO	=400;
	Integer DEBUG	=500;
	Integer TRACE	=600;
	Integer ASSERT =700;	
	Integer VERBOSE =900;	
	Integer ALL	=Integer.MAX_VALUE;
}
