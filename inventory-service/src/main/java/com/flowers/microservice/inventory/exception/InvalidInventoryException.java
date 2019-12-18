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
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class InvalidInventoryException extends IllegalStateException {

	private static final long serialVersionUID = -2706211414486833150L;

	public InvalidInventoryException(String s) {
        super(s);
    }
}