/**
 * 
 */
package com.flowers.microservice.order.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.flowers.microservice.beans.Order;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */

public interface OrderMongoRepository extends MongoRepository<Order, String> {
	

}