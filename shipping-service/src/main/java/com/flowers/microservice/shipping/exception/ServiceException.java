package com.flowers.microservice.shipping.exception;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
public class ServiceException extends Exception{

	private static final long serialVersionUID = -3222758099418392504L;

	public ServiceException(String msg) {
		
		super(msg);
	}
	
}