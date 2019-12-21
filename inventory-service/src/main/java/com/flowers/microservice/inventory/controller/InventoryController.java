/**
 * 
 */
package com.flowers.microservice.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.flowers.microservice.beans.Inventory;
import com.flowers.microservice.inventory.service.InventoryService;
import com.flowers.microservice.notification.health.HealthIndicatorService;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;
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
@ConfigurationProperties
public class InventoryController {

    
	@Autowired
	private HealthIndicatorService healthIndicatorService;  
	
    @Autowired
	private InventoryService inventoryService;
        
    @Value(value = "${app.http.timeout}")
    private long timeout;
    
    @Value("${app.info.description}")
    private String serviceInfo;   
    
    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @GetMapping("/service-instances/instanceid")
    public StringBuffer getEurekaStatus() {
        
        return new StringBuffer("instance id: " + instanceId);
    }  
    
    @Autowired
    private DiscoveryClient discoveryClient;

	@RequestMapping(value = "/health",  method = RequestMethod.GET)
	public InstanceStatus health() {
		return healthIndicatorService.health();
	}
	
	@RequestMapping(value = "/info",  method = RequestMethod.GET)
	public String information() {
		return String.format("Service description: %s. Health status %s", serviceInfo,  healthIndicatorService.health());
	}	
    
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