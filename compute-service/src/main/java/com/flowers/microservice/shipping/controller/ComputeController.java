/**
 * 
 */
package com.flowers.microservice.shipping.controller;

import javax.validation.Valid;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flowers.microservice.shipping.domain.Order;
import com.flowers.microservice.shipping.facade.CalculateFacade;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */

@RestController
public class ComputeController {

	@RequestMapping(value = "/compute/tax", method = RequestMethod.POST)
	public Double computeOrderTax(@Valid @RequestBody final Order order) {
		return CalculateFacade.calculateOrderTax(order);
	}
	
	@RequestMapping(value = "/compute/shipping", method = RequestMethod.POST)
	public Double computeOrderShipping(@Valid @RequestBody final Order order) {
		return CalculateFacade.calculateOrderShipping(order);
	}
	
	@RequestMapping(value = "/compute/deliverydate", method = RequestMethod.POST)
	public String computeOrderDeliveryDate(@Valid @RequestBody final Order order) {
		return CalculateFacade.calculateOrderDeliveryDate(order);
	}
	
	@RequestMapping(value = "/compute/total", method = RequestMethod.POST)
	public Double computeOrderTotal(@Valid @RequestBody final Order order) {
		return CalculateFacade.calculateOrderTotal(order);
	}
}


