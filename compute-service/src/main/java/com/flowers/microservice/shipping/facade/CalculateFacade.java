/**
 * 
 */
package com.flowers.microservice.shipping.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flowers.microservice.shipping.domain.Order;
import com.flowers.microservice.shipping.domain.ShippingRate;
import com.flowers.microservice.shipping.domain.TaxRate;
import com.flowers.microservice.shipping.service.ComputeService;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
@Component
public class CalculateFacade {

    @Autowired
	private static ComputeService service;
    
	public static Double calculateOrderTax(Order order){
		
		double taxrate = effectiveTax(order);
		double total  = order.getItemIdList().stream().map(s -> {
			return s.getUnitPrice() * s.getQuantity() * taxrate;
		}).reduce(0.0, Double::sum);
		
		return total;
	}
	
	public static Double calculateOrderTotal(Order order){

		double total  = order.getItemIdList().stream().map(s -> {
			return s.getUnitPrice() * s.getQuantity();
		}).reduce(0.0, Double::sum);
		
		return total;
	}
	
	public static Double calculateOrderShipping(Order order){
		double total  = order.getItemIdList().stream().map(s -> {
			return s.getUnitPrice() * s.getQuantity();
		}).reduce(0.0, Double::sum);
		
		return total;
	}
	
	public static String calculateOrderDeliveryDate(Order order){
		
		return order.getDeliveryDate();
	}
	
	public static double effectiveTax(Order order) {
		
		TaxRate taxrate = service.findOneTaxRate(order.getAddress().getPostcode());
		return taxrate.getStaterate();
	}
	public static double effectiveShipping(Order order) {
		
		ShippingRate shiprate = service.findOneShippingRate(order.getAddress().getPostcode());
		return shiprate.getRate();
	}
}
