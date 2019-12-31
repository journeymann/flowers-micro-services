/**
 * 
 */
package com.flowers.microservice.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.flowers.microservice.product.domain.Product;
import com.flowers.microservice.product.service.ProductService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.validation.Valid;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@RestController
@RequestMapping(path = "/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
    @HystrixCommand(fallbackMethod = "fallbackProdById")
	@RequestMapping(path = "/find", method = RequestMethod.GET)
	public Product getProductById(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "name", required = false) String name) {
		return name!=null? productService.findProductById(name) : productService.findProductById(id);
	}

    @HystrixCommand(fallbackMethod = "fallbackProdByName")
	@RequestMapping(path = "/find", method = RequestMethod.GET)
	public Product getProductByName(@RequestParam(value = "id", required = false) String id,
			@RequestParam(value = "name", required = false) String name) {
		return name!=null? productService.findProductById(id) : productService.findProductByName(name);
	}
    
	@RequestMapping(path = "/delete/{id}", method = RequestMethod.PUT)
	public void deleteProduct(@PathVariable String id) {
		productService.delete(id);
	}

	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public Product createProduct(@Valid @RequestBody Product product) {
		return productService.create(product);
	}

	@RequestMapping(path = "/update/{id}", method = RequestMethod.POST)
	public Product updateProduct(@Valid @PathVariable String id, @Valid @RequestBody Product product) {
		return productService.update(id, product);
	}

    public Product fallbackProdById() {
        return new Product();
    }

    public Product fallbackProdByName() {
        return new Product();
    }
    
    @GetMapping("/all-products")
    @HystrixCommand(fallbackMethod = "fallbackAllProducts")
    @CrossOrigin(origins = "*")    
    public Collection<Product> getProducts() {
        return productService.findAllProducts()
                .stream()
                .filter(p -> p.isActive())
                .collect(Collectors.toList());
    }    

    public Collection<Product> fallbackAllProducts() {
        return new ArrayList<Product>();
    }
   
}