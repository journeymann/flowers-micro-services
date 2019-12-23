/**
 * 
 */
package com.flowers.microservice.shipping.facade;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.flowers.microservice.beans.ShippingRate;
import com.flowers.microservice.beans.Order;
import com.flowers.microservice.beans.TaxRate;
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
		double total  = order.getItemList().stream().map(s -> {
			return s.getUnitPrice() * s.getQuantity() * taxrate;
		}).reduce(0.0, Double::sum);
		
		return total;
	}
	
	public static Float calculateOrderTotal(Order order){

		Float total  = order.getItemList().stream().map(s -> {
			return s.getUnitPrice() * s.getQuantity();
		}).reduce(0.0F, Float::sum);
		
		return total;
	}
	
	public static Float calculateOrderShipping(Order order){
		Float total  = order.getItemList().stream().map(s -> {
			return s.getUnitPrice() * s.getQuantity();
		}).reduce(0.0F, Float::sum);
		
		return total;
	}
	
	public static LocalDate calculateOrderDeliveryDate(Order order){
		
		return order.getDeliveryDate();
	}
	
	public static double effectiveTax(Order order) {
		
		TaxRate taxrate = service.findOneTaxRate(order.getAddress().getPostCode());
		return taxrate.getRate();
	}
	public static double effectiveShipping(Order order) {
		
		ShippingRate shiprate = service.findOneShippingRate(order.getAddress().getPostCode());
		return shiprate.getRate();
	}
}
