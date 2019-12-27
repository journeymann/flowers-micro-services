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
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flowers.microservice.beans.Order;
import com.flowers.microservice.beans.Shipment;
import com.flowers.microservice.shipping.facade.CalculateFacade;
import com.flowers.microservice.shipping.health.HealthIndicatorService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */

@RestController
//@EnableFeignClients
@ConfigurationProperties
@Api(value="/shipping",description="Shipping Rates",produces ="application/json")
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
        
    @ApiOperation(value="get service information",response=String.class)
    @ApiResponses(value={
	    @ApiResponse(code=200,message="Rate Details Retrieved",response=String.class),
	    @ApiResponse(code=500,message="Internal Server Error"),
	    @ApiResponse(code=404,message="Rate not found")})
	@GetMapping(value = "/info")
	public String information(Model model) {
	    Application application = eurekaClient.getApplication("shipping-service");
	    List<InstanceInfo> instanceInfo = application.getInstances();
	    String hostname = instanceInfo.get(0).getHostName();
	    String port = instanceInfo.stream().map(p -> String.valueOf(p.getPort())).collect(Collectors.joining(","));
	    InstanceStatus status = healthIndicatorService.health();
	    
	    model.addAttribute("name", application.getName());
	    model.addAttribute("hostname", hostname);
	    model.addAttribute("port", port);
	    model.addAttribute("status", status);
	    model.addAttribute("info", serviceInfo);

        return "info-view";
	}	
	    
    @ApiOperation(value="get order shipping cost",response=Float.class)
    @ApiResponses(value={
	    @ApiResponse(code=200,message="Order Rate Details Retrieved",response=Shipment.class),
	    @ApiResponse(code=500,message="Internal Server Error"),
	    @ApiResponse(code=404,message="Order Rate not found")})
    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/shipping", method = RequestMethod.POST)
	public Float computeOrderShipping(@Valid @RequestBody final Order order) {
		return CalculateFacade.calculateOrderShipping(order);
	}
	
    @ApiOperation(value="get shipping delivery date",response=LocalDate.class)
    @ApiResponses(value={
	    @ApiResponse(code=200,message="Delivery Date Retrieved",response=LocalDate.class),
	    @ApiResponse(code=500,message="Internal Server Error"),
	    @ApiResponse(code=404,message="Delivery Date not found")})
    @HystrixCommand(fallbackMethod = "fallbackdelivery")
	@RequestMapping(value = "/deliverydate", method = RequestMethod.POST)
	public LocalDate computeOrderDeliveryDate(@Valid @RequestBody final Order order) {
		return CalculateFacade.calculateOrderDeliveryDate(order);
	}
	
    @ApiOperation(value="get order total",response=Float.class)
    @ApiResponses(value={
	    @ApiResponse(code=200,message="Order Total Retrieved",response=Float.class),
	    @ApiResponse(code=500,message="Internal Server Error"),
	    @ApiResponse(code=404,message="Order Total not found")})
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


