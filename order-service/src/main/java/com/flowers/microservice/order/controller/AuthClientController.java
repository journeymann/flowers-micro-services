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
import org.springframework.web.bind.annotation.RestController;

import com.flowers.microservice.order.domain.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
@RestController
public class AuthClientController  {
	
    @Value("${service.authSearch.serviceId:100000}")
    private String searchServiceId;
    
    private final String TYPE_KEY = "auth";
    
    @HystrixCommand(fallbackMethod = "fallbackById")
	@RequestMapping(path = "/order/auth/{Id}", method = RequestMethod.GET)
    public User find(@PathVariable Long Id) {

        return null;
    }
        
    @HystrixCommand(fallbackMethod = "fallbackAll")
	@RequestMapping(path = "/order/auth/all", method = RequestMethod.GET)
    public Collection <User> findUsers() {

		return null;
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


