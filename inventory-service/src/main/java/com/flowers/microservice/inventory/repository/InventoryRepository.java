/**
 * 
 */
package com.flowers.microservice.inventory.repository;

import org.springframework.stereotype.Repository;

import com.flowers.microservice.beans.Product;

import org.springframework.data.mongodb.repository.MongoRepository;
/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
@Repository
public interface InventoryRepository extends MongoRepository<Product, String> {


}
