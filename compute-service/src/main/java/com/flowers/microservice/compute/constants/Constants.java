/**
 * 
 */
package com.flowers.microservice.compute.constants;

import java.util.regex.Pattern;

/**
 * @author cgordon
 * @created 12/18/2017
 * @version 1.0
 *
 * constants are maintained here. general policy to not have or minimize rogue string literals throughout the application
 *
 */
public final class Constants {
	
	public static final String _WELL_FORMED_EMPTY_DOCUMENT = "<?xml version='1.0'?><_/>";
	public final static String _DELIMITER = "::";	
	public static final String _WHITESPACE = " ";
	public static final String _BLANK = "";
	public static final String _YES = "Y";
	public static final String _NO = "N";
	public static final String _EMPTY = "EMPTY_COLLECTION";
	public static final String DATEFORMAT_ISO8601 = "yyyy-MM-dd'T'HH:mm'Z'";
	public static final String UTF8_BOM = "\uFEFF";
	
	public static final Pattern XML_ENTITY_PATTERN = Pattern.compile("\\&\\#(?:x([0-9a-fA-F]+)|([0-9]+))\\;");
	
	public static final byte ZERO = (byte) 0x0;
	public static final byte EXCEPTION_CODE_IO = (byte) 0x2;
	public static final byte EXCEPTION_CODE_GENERAL = (byte) 0x3;
	public static final byte EXCEPTION_CODE_ARG_UNDERFLOW = (byte) 0x4;
	
	public static final short HTTP_KEEP_ALIVE_TIME = (short) 1 * 30 * 1000;
	public static final short HTTP_SO_TIMEOUT = (short) 2000;
	public static final short MAX_PAYLOAD_LENGTH = (short) 8192 ; /** OWASP security recommended ceiling on payload size*/
	public static final short HTTP_CONNECTION_TIME_OUT = (short) 1 * 30 * 1000;
	public static final short DEFAULT_TIMEOUT = (short) 0x1000;
	public static final short DEFAULT_THREAD_SLEEP = (short) 0x100;

	public static final String REGEXP_VALID_XML = "[^\\x20-\\x7e\\x0A]";
	public static final String REGEXP_VALID_EMAIL = "^(.+)@(.+)$";	
	public static final String REGEXP_VALID_PHONE = "^(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}$";
	public static final String REGEXP_VALID_FLAG = "^[Y|N]{1}$";
	public static final String REGEXP_VALID_ALIAS_LABEL = "^[email|phone]{1}$";
	public static final String REGEXP_TRUE = "^[Y|YES|T|TRUE|1]{1}$";
	public static final String REGEXP_VALID_URL = "^(http://|https://)?(www.)?([a-zA-Z0-9@:%_\\+.~#?&//=-]+).[a-zA-Z0-9@:%_\\+.~#?&//=-]+]*.[a-z]{3}.?([a-zA-Z0-9@:%_\\+.~#?&//=-]+)?$";	
		
	/**
	 *  Declare class final and constructor private to defeat instantiation and extension
	 */
	private Constants(){
		
	}
}	
	