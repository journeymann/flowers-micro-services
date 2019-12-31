/**
 * 
 */
package com.flowers.microservice.database.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.flowers.microservice.database.model.Product;
import com.flowers.microservice.database.service.DatabaseService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@RestController
@RequestMapping("/data/product")
public class DatabaseController {

	@Autowired
	private DatabaseService dataService;

    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public Product createProduct(@Valid @RequestBody final Product product) {
		return dataService.createProduct(product);
	}
	
    @HystrixCommand(fallbackMethod = "fallback")    
	@RequestMapping(value = "/read/{id}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable final String id) {
		return dataService.findProductById(id);
	}
	
    @HystrixCommand(fallbackMethod = "fallbackAllProducts")
	@RequestMapping(value = "/all}", method = RequestMethod.GET)
	public List<Product> getAllProduct() {
		return dataService.findAllProductList();
	}
	
    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public Product updateProduct(@PathVariable final String id, @Valid @RequestBody final Product product) {
		return dataService.updateProduct(id, product);
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public void deleteProduct(@PathVariable final String id) {
		dataService.deleteProduct(id);
	}
	
    public Product fallback() {
        return new Product();
    }
    
    public Collection<Product> fallbackAllProducts() {
        return new ArrayList<Product>();
    }
}


