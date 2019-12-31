/**
 * 
 */
package com.flowers.microservice.database.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import com.flowers.microservice.database.model.Product;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@Repository
@Component
public interface ProductJpaRepository extends CrudRepository<Product, String> {
	
	public default Product findByName(String name){
		
		List<Product> products = (List<Product>) findAll();
		
		return products.stream().filter(product -> product.getProductName().equals(name)).findFirst().orElseGet(Product::new);
	}
	
	public default Product update(Product product){
		
		return save(product);
	}	

	public default Product reliable() {

		Product dproduct = new Product();
		dproduct.setProductName("Default Product To Avoid Cascade Error");
		
		return dproduct;
	}

}