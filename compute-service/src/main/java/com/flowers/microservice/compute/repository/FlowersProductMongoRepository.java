/**
 * 
 */
package com.flowers.microservice.compute.repository;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import com.flowers.microservice.compute.model.Product;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@Repository
public interface FlowersProductMongoRepository extends MongoRepository<Product, String> {

	@Query("{productName:'?0'}")
	Product findCustomByProductName(String name);		

	@Query("{productName: { $regex: ?0 } })")
	List<Product> findCustomByRegExProductName(String name);

	public default List<Product> findCustomListProductName(String name){
		
		return this.findCustomByRegExProductName(".*name*");
	};
	
	public default List<Product> findAllByName(String name){

		return findAllProducts().stream().filter(product -> product.getProductName().matches(String.format(".*%s*", name))).collect(Collectors.toList());
	}

	public default Product findById(String key){

		return findOne(key);
	}

	public default Product findByName(String name){

		return findAllProducts().stream().filter(product -> product.getProductName().equalsIgnoreCase(name)).findFirst().orElse(new Product());
	}
	
	public default Product update(Product product){

		return save(product);
	}

	public default List<Product> findAllProducts(){
		return (List<Product>)findAll();
	}	

	public static <T> void copyListElements(final List<T> list, Supplier<Collection<T>> targetCollection) {
		list.forEach(targetCollection.get()::add);
	}		

}