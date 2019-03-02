/**
 * 
 */
package com.flowers.microservice.compute.service;

import java.util.List;

import com.flowers.microservice.compute.model.Product;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

public interface ComputeService {
	
	Product createProduct(Product product);
	
	Product findProductById(String productid);

	List<Product> findAllProductList();
	
	Product updateProduct(Product product);
	
	void deleteProduct(String productid);
}
