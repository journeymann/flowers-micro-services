/**
 * 
 */
package com.flowers.microservice.shipping.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flowers.microservice.beans.Order;
import com.flowers.microservice.shipping.facade.CalculateFacade;
import com.flowers.microservice.shipping.health.HealthIndicatorService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */

@RestController
@EnableFeignClients
@ConfigurationProperties
public class ShippingController {
	
   
    @Autowired
    private EurekaClient eurekaClient;

	@Autowired
	private HealthIndicatorService healthIndicatorService;
   
    @RequestMapping("/service-instances/{applicationName}")
    public List<InstanceInfo> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.eurekaClient.getApplication(applicationName).getInstances();
    }    

    @Value("${eureka.instance.instance-id}")
    private String instanceId;
    
    @Value("${app.info.description}")
    private String serviceInfo;    
    
    @Value("${spring.application.name}")
    private String springApplicationName;   

    @GetMapping("/service-instances/instanceid")
    public StringBuffer getEurekaStatus() {
        return new StringBuffer("instance id: " + instanceId);
    }  
        
	@RequestMapping(value = "/health",  method = RequestMethod.GET)
	public InstanceStatus health() {
		return healthIndicatorService.health();
	}
	
	@RequestMapping(value = "/info",  method = RequestMethod.GET)
	public String information(Model model) {
	    Application application = eurekaClient.getApplication(springApplicationName);
	    List<InstanceInfo> instanceInfo = application.getInstances();
	    String hostname = instanceInfo.get(0).getHostName();
	    String port = instanceInfo.stream().map(p -> String.valueOf(p.getPort())).collect(Collectors.joining(","));
	    InstanceStatus status = healthIndicatorService.health();
	    
	    model.addAttribute("service-name", application.getName());
	    model.addAttribute("hostname", hostname);
	    model.addAttribute("port", port);
	    model.addAttribute("status", status);
	    model.addAttribute("info", serviceInfo);

        return "info-view";
	}	
	
    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/shipping", method = RequestMethod.POST)
	public Float computeOrderShipping(@Valid @RequestBody final Order order) {
		return CalculateFacade.calculateOrderShipping(order);
	}
	
    @HystrixCommand(fallbackMethod = "fallbackdelivery")
	@RequestMapping(value = "/deliverydate", method = RequestMethod.POST)
	public LocalDate computeOrderDeliveryDate(@Valid @RequestBody final Order order) {
		return CalculateFacade.calculateOrderDeliveryDate(order);
	}
	
    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/total", method = RequestMethod.POST)
	public Float computeOrderTotal(@Valid @RequestBody final Order order) {
		return CalculateFacade.calculateOrderTotal(order);
	}
    
    public Double fallback() {
        return Double.parseDouble("0.01");
    }
    
    public String fallbackdelivery() {
        return String.valueOf(LocalDateTime.now().plusDays(3));
    }
}


