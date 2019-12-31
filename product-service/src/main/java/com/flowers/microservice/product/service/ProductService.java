/**
 * 
 */
package com.flowers.microservice.product.service;

import java.util.List;

import com.flowers.microservice.product.domain.Product;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

public interface ProductService {

	public Product findProductByName(String name);

	public Product findProductById(String id);
	
	public List<Product> findAllProducts();	
	
	public Product create(Product Product);

	public Product update(String id, Product Product);
	
	public void delete(String id);	
}