/**
 * 
 */
package com.flowers.microservice.shipping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.flowers.microservice.shipping.domain.ShippingRate;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */

@Repository
public interface ShippingRateMongoRepository extends MongoRepository<ShippingRate, String> {

	
}