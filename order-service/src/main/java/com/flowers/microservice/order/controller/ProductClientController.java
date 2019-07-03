/**
 * 
 */
package com.flowers.microservice.order.controller;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.flowers.microservice.order.model.Product;
import com.flowers.microservice.order.controller.AbstractClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */
public class ProductClientController extends AbstractClient {
	
    @Value("${service.productSearch.serviceId}")
    private String searchServiceId;
    
    private final String TYPE_KEY = "product";
    
    @HystrixCommand(fallbackMethod = "fallbackById")
    @RequestMapping("/order/product/{Id}")
    public Product find(@PathVariable Long productId) {

        return (Product)super.find(productId);
    }
        
    @HystrixCommand(fallbackMethod = "fallbackAll")
    @RequestMapping("/order/product/all")
    public Collection <Product> findProducts() {

		return convertModelList(super.findAll(), Product.class);
    }
    
    public final Product fallbackById() {
        return new Product();
    }
    
    public final Collection<Product> fallbackAll() {
        return new ArrayList<Product>();
    }    
    
    public final String getTypeKey() {
    	return TYPE_KEY;
    };
    
    public final String getSearchServiceId() {
    	return searchServiceId;
    };
    
}

