/**
 * 
 */
package com.flowers.microservice.database.service;

import java.util.List;

import com.flowers.microservice.database.model.Product;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

public interface DatabaseService {
	
	Product createProduct(Product product);
	
	Product findProductById(String productid);

	List<Product> findAllProductList();
	
	Product updateProduct(String productid, Product product);
	
	void deleteProduct(String productid);
}
