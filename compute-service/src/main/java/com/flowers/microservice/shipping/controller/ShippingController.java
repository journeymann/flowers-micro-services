/**
 * 
 */
package com.flowers.microservice.shipping.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flowers.microservice.shipping.domain.Order;
import com.flowers.microservice.shipping.facade.CalculateFacade;
import com.flowers.microservice.shipping.health.HealthIndicatorService;
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
@ConfigurationProperties
public class ShippingController {
	
    @Autowired
    private DiscoveryClient discoveryClient;
    
	@Autowired
	private HealthIndicatorService healthIndicatorService;
   
    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }    

    @Value("${eureka.instance.instance-id}")
    private String instanceId;
    
    @Value("${app.info.description}")
    private String serviceInfo;        

    @GetMapping("/service-instances/instanceid")
    public StringBuffer getEurekaStatus() {
        
        return new StringBuffer("instance id: " + instanceId);
    }  
        
	@RequestMapping(value = "/health",  method = RequestMethod.GET)
	public InstanceStatus health() {
		return healthIndicatorService.health();
	}
	
	@RequestMapping(value = "/info",  method = RequestMethod.GET)
	public String information() {
		return String.format("Service description: %s. Health status %s", serviceInfo,  healthIndicatorService.health());
	}	
	
    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/shipping", method = RequestMethod.POST)
	public Double computeOrderShipping(@Valid @RequestBody final Order order) {
		return CalculateFacade.calculateOrderShipping(order);
	}
	
    @HystrixCommand(fallbackMethod = "fallbackdelivery")
	@RequestMapping(value = "/deliverydate", method = RequestMethod.POST)
	public String computeOrderDeliveryDate(@Valid @RequestBody final Order order) {
		return CalculateFacade.calculateOrderDeliveryDate(order);
	}
	
    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/total", method = RequestMethod.POST)
	public Double computeOrderTotal(@Valid @RequestBody final Order order) {
		return CalculateFacade.calculateOrderTotal(order);
	}
    
    public Double fallback() {
        return Double.parseDouble("0.01");
    }
    
    public String fallbackdelivery() {
        return String.valueOf(LocalDateTime.now().plusDays(3));
    }
}


