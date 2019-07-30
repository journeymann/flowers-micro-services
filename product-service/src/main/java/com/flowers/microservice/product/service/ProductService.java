/**
 * 
 */
package com.flowers.microservice.product.service;

import java.util.List;

import com.flowers.microservice.product.domain.Product;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */

public interface ProductService {

	Product createProduct(Product product);
	
	Product findProductById(String productid);

	List<Product> findAllProductList();
	
	Product updateProduct(String productid, Product product);
	
	void deleteProduct(String productid);

	List<Product> findAllProductListPaginated(int pageNumber, int pageSize);

	Product findOneById(String id);

	List<Product> findById(String id);

	Product updateOne(Product product);
}