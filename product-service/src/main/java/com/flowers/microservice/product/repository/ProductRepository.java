/**
 * 
 */
package com.flowers.microservice.product.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.flowers.microservice.product.domain.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {

	public default Product findByName(String name){

		List<Product> products = (List<Product>) findAll();

		return products.stream().filter(product -> product.getName().equals(name)).findFirst().orElseGet(Product::new);
	}

	public default Product update(Product product){

		return save(product);
	}
	
	public default List<Product> findAllProducts(){
		return (List<Product>) findAll();
	}
}
