/**
 * 
 */
package com.flowers.microservice.compute.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flowers.microservice.compute.facade.CalculateFacade;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@RestController
@RequestMapping("/compute")
public class ComputeController {

	@RequestMapping(value = "/tax/{ordernum}", method = RequestMethod.GET)
	public Double computeOrderTax(@PathVariable final String ordernum) {
		return CalculateFacade.calculateOrderTax(ordernum);
	}
	
	@RequestMapping(value = "/shipping/{ordernum}", method = RequestMethod.GET)
	public Double computeOrderShipping(@PathVariable final String ordernum) {
		return CalculateFacade.calculateOrderShipping(ordernum);
	}
	
	@RequestMapping(value = "/deliverydate/{ordernum}", method = RequestMethod.GET)
	public String computeOrderDeliveryDate(@PathVariable final String ordernum) {
		return CalculateFacade.calculateOrderDeliveryDate(ordernum);
	}
	
	@RequestMapping(value = "/total/{ordernum}", method = RequestMethod.GET)
	public Double computeOrderTotal(@PathVariable final String ordernum) {
		return CalculateFacade.calculateOrderTotal(ordernum);
	}
}


