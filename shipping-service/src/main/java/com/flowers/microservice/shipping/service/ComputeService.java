/**
 * 
 */
package com.flowers.microservice.shipping.service;

import java.util.List;

import com.flowers.microservice.beans.ShippingRate;
import com.flowers.microservice.beans.TaxRate;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
public interface ComputeService {
	
	ShippingRate findOneShippingRate(String state);
	TaxRate findOneTaxRate(String state);
	List<ShippingRate> findShippingRates();
	ShippingRate findOrderShippingRate(String orderid);
}
