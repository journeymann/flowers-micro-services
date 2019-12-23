/**
 * 
 */
package com.flowers.microservice.product.repository;

import org.springframework.stereotype.Repository;
import com.flowers.microservice.beans.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
@Repository
public interface ItemRepository extends MongoRepository<Item, String> {


}
