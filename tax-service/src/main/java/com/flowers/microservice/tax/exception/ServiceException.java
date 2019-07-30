package com.flowers.microservice.tax.exception;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  10/12/2017 
 * @version 1.0
 *
 */
public class ServiceException extends Exception{

	private static final long serialVersionUID = -3222758099418392504L;

	public ServiceException(String msg) {
		
		super(msg);
	}
	
}