/**
 * 
 */
package com.flowers.microservice.shipping.service;

import com.flowers.microservice.shipping.domain.ShippingRate;
import com.flowers.microservice.shipping.domain.TaxRate;

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

}
