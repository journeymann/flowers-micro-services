/**
 * 
 */
package com.flowers.microservice.database.service;

import com.flowers.microservice.database.model.Product;
import com.flowers.microservice.database.repository.ProductJpaRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@Service
public class DatabaseServiceImpl implements DatabaseService {

	@Autowired
	private ProductJpaRepository repository;


	public Product createProduct(Product product){
		
		return repository.save(product);
	};
	
	public Product findProductById(String productid){
		
		return (Product) repository.findOne(productid);
	};

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Product> findAllProductList(){
		
		List<Product> list = new ArrayList<Product>();
		
		copyListElements((List)repository.findAll(),(Supplier<Collection<Product>>)list);
		
		return list;
	}
	
	public Product updateProduct(String productid, Product product){
		
		return repository.findOne(productid) != null? repository.save(product) : product;
	};
	
	public void deleteProduct(String productid){
		
		repository.delete(productid);
	};
	
    public static <T> void copyListElements(final List<T> list, Supplier<Collection<T>> targetCollection) {
        list.forEach(targetCollection.get()::add);
    }
}
	
