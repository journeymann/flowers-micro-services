/**
 * 
 */
package com.flowers.microservice.product.controller;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.flowers.microservice.product.domain.Order;
import com.flowers.microservice.product.controller.AbstractClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */
public class OrderClientController extends AbstractClient {
	
    @Value("${service.orderSearch.serviceId}")
    private String searchServiceId;
    
    private final String TYPE_KEY = "order";
    
    @HystrixCommand(fallbackMethod = "fallbackById")
	@RequestMapping(path = "/product/order/{Id}", method = RequestMethod.GET)
    public Order find(@PathVariable Long Id) {

        return (Order)super.find(Id);
    }
        
    @HystrixCommand(fallbackMethod = "fallbackAll")
	@RequestMapping(path = "/product/order/all", method = RequestMethod.GET)
    public Collection <Order> findProducts() {

		return convertModelList(super.findAll(), Order.class);
    }
    
    public Order fallbackById() {
        return new Order();
    }
    
    public Collection<Order> fallbackAll() {
        return new ArrayList<Order>();
    }    
    
    public final String getTypeKey() {
    	return TYPE_KEY;
    };
    
    public final String getSearchServiceId() {
    	return searchServiceId;
    };
    
}
