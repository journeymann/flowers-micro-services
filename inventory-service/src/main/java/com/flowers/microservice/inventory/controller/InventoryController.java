/**
 * 
 */
package com.flowers.microservice.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.flowers.microservice.inventory.domain.Inventory;
import com.flowers.microservice.inventory.service.InventoryService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */

@RestController
@PreAuthorize("hasAuthority('ROLE_TRUSTED_CLIENT')")
@RequestMapping(path = "/inventory")
public class InventoryController {

    @Autowired
	private InventoryService inventoryService;
        
    @Value(value = "${http.timeout:5}")
    private long timeout;

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @GetMapping("/service-instances/instanceid")
    public StringBuffer getEurekaStatus() {
        
        return new StringBuffer("instance id: " + instanceId);
    }  
    
    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }
    
    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/inventory/read/{productid}", method = RequestMethod.GET)
	public Inventory getProduct(@PathVariable final String productid) {
		return inventoryService.findProductCountById(productid);
	}
	
    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/inventory/update/{productid}", method = RequestMethod.POST)
	public Inventory updateProduct(@PathVariable final String productid, @PathVariable final Long count) {
		return inventoryService.updateProductCount(productid, count);
	}
	
    public Inventory fallback() {
        return new Inventory();
    }
  
}