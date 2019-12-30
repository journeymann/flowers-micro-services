package com.flowers.microservice.shipping.init;

import java.time.LocalDate;
import java.util.stream.Collectors;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.flowers.microservice.beans.ShippingRate;
import com.flowers.microservice.beans.State;
import com.flowers.microservice.constants.ShippingMethod;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
public class InitialiseTest {

	
	   public static void init(MongoTemplate mongoTemplate) { 
	    	
			State.States.stream().map(r -> {
				ShippingRate rate = new ShippingRate( r.getCode(), "NJ", LocalDate.now(), LocalDate.now(), ShippingMethod.UPS);
				mongoTemplate.save(rate);
				return rate;
			}).collect(Collectors.toList());
		   
	    }
}
