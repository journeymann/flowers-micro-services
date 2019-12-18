package com.flowers.microservice.inventory.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;
/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ApplicationException extends IllegalStateException {

	private static final long serialVersionUID = -3993935771017802931L;

	public ApplicationException(String s) {
        super(s);
    }
}

