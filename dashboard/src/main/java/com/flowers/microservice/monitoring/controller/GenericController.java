/**
 * 
 */
package com.flowers.microservice.monitoring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flowers.microservice.monitoring.health.HealthIndicatorService;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
@RestController
@ConfigurationProperties
public class GenericController {
	    
		@Autowired
		private HealthIndicatorService healthIndicatorService;   
    
	    @Value("${app.info.description}")
	    private String serviceInfo;        
	    
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

	    @Value("${eureka.instance.instance-id}")
	    private String instanceId;

	    @GetMapping("/service-instances/instanceid")
	    public StringBuffer getEurekaStatus() {
	        
	        return new StringBuffer("instance id: " + instanceId);
	    }  
	    
}