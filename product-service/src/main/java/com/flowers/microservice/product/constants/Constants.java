/**
 * 
 */
package com.flowers.microservice.product.constants;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

public final class Constants {

	public static final short DEFAULT_TIMEOUT = (short) 0x1000;
	public static final byte EXCEPTION_CODE_IO = (byte) 0x2;
	public static final byte EXCEPTION_CODE_GENERAL = (byte) 0x3;
	public static final byte EXCEPTION_CODE_ARG_UNDERFLOW = (byte) 0x4;
	public static final short HTTP_SO_TIMEOUT = (short) 2000;	
	
	public static final byte ZERO = (byte) 0x0;
	public static final String UTF8_BOM = "\uFEFF";		
	
	public static final String REGEXP_VALID_EMAIL = "^(.+)@(.+)$";	
	public static final String REGEXP_VALID_FLAG = "^[Y|y|YES|T|t|TRUE|1|N|n|NO|F|f|FALSE|0]{1}$";	
	public static final String REGEXP_VALID_BOOLFLAG = "^[true|false]{1}$";
	
	public final static String ERROR_SERVICE_MESSAGE = "Service not Found"; 
	
	/**
	 *  Declare class final and constructor private to defeat instantiation and extension
	 */
	private Constants(){
		
	}	
}
