package com.flowers.microservice.product.constants;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 * constants are maintained here. general policy to not have or minimize rogue string literals throughout the application
 *
 */
public final class Constants {
	
	public static final short HTTP_KEEP_ALIVE_TIME = (short) 1 * 30 * 1000;
	public static final short HTTP_SO_TIMEOUT = (short) 2000;
	public static final short MAX_PAYLOAD_LENGTH = (short) 8192 ; /** OWASP security recommended ceiling on payload size*/
	public static final short HTTP_CONNECTION_TIME_OUT = (short) 1 * 30 * 1000;
	public static final short DEFAULT_TIMEOUT = (short) 0x1000;
	public static final short DEFAULT_THREAD_SLEEP = (short) 0x100;
		
	/**
	 *  Declare class final and constructor private to defeat instantiation and extension
	 */
	private Constants(){
		
	}
}	
	
