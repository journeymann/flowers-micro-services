/**
 * 
 */
package com.flowers.microservice.order.controller;

import java.util.ArrayList;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.flowers.microservice.order.model.User;
import com.flowers.microservice.order.controller.AbstractClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */
public class AuthClientController extends AbstractClient {
	
    @Value("${service.authSearch.serviceId}")
    private String searchServiceId;
    
    private final String TYPE_KEY = "auth";
    
    @HystrixCommand(fallbackMethod = "fallbackById")
	@RequestMapping(path = "/order/auth/{Id}", method = RequestMethod.GET)
    public User find(@PathVariable Long Id) {

        return (User)super.find(Id);
    }
        
    @HystrixCommand(fallbackMethod = "fallbackAll")
	@RequestMapping(path = "/order/auth/all", method = RequestMethod.GET)
    public Collection <User> findUsers() {

		return convertModelList(super.findAll(), User.class);
    }
    
    public final User fallbackById() {
        return new User();
    }
    
    public final Collection<User> fallbackAll() {
        return new ArrayList<User>();
    }    
    
    public final String getTypeKey() {
    	return TYPE_KEY;
    };
    
    public final String getSearchServiceId() {
    	return searchServiceId;
    };
    
}
