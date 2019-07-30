package com.flowers.microservice.order.exception;

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
public class PaymentDeclinedException extends IllegalStateException {

	private static final long serialVersionUID = -2756758826826367945L;

	public PaymentDeclinedException(String s) {
        super(s);
    }
}

