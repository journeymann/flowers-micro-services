/**
 * 
 */
package com.flowers.microservice.shipping.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.flowers.microservice.beans.Order;
import com.flowers.microservice.beans.ShippingRate;
import com.flowers.microservice.shipping.facade.CalculateFacade;
import com.flowers.microservice.shipping.health.HealthIndicatorService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.*;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 * swagger : http://localhost:5002/shipping/swagger-ui.html#/
 * 
 */
@SwaggerDefinition(
        info = @Info(
                description = "Gets shipping information and calculates rates",
                version = "V12.0.12",
                title = "The Shipping API",
                termsOfService = "http://terms.html",
                contact = @Contact(
                   name = "Roger Moore", 
                   email = "roger.mooree@acme.com", 
                   url = "http://www.acme.com"
                ),
                license = @License(
                   name = "Apache 2.0", 
                   url = "http://www.apache.org/licenses/LICENSE-2.0"
                )
        ),
        consumes = {"application/json"},
        produces = {"application/json"},
        schemes = {SwaggerDefinition.Scheme.HTTP, SwaggerDefinition.Scheme.HTTPS},
        tags = {
                @Tag(name = "Private", description = "Tag used to denote operations as private")
        }, 
        externalDocs = @ExternalDocs(value = "Web services design best practises", url = "http://somewebsitehere.com/best_practise.html")
)
@RestController
//@EnableFeignClients
@ConfigurationProperties
@Api(value="/shipping",description="Shipping Rates",produces ="application/json")
@Produces({"application/json"})
@Consumes({"application/json"})
//@PreAuthorize("hasAuthority('ROLE_TRUSTED_CLIENT')")
@RefreshScope
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
        
    @GetMapping(value = "/info")
	public String information(@ApiParam(value = "Model object", required = true)  Model model) {
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
	    
    @Path("/shipping")
    @ApiOperation(value="get order shipping cost",response=Float.class)
    @ApiResponses(value={
	    @ApiResponse(code=200,message="Order Rate Details Retrieved",response=Float.class),
	    @ApiResponse(code=400,message="Resource Not Found", responseHeaders = @ResponseHeader(name = "X-Rack-Cache", description = "Explains whether or not a cache was used", response = Float.class)),
	    @ApiResponse(code=500,message="Internal Server Error"),
	    @ApiResponse(code=404,message="Order Rate not found")})
    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/shipping", method = RequestMethod.POST)
	public Float computeOrderShipping(@Valid @RequestBody @ApiParam(value = "Order object", required = true) final Order order) {
		return CalculateFacade.calculateOrderShipping(order);
	}
    
    @Path("/rates")
    @ApiOperation(value="get shipping rate table",response=List.class)
    @ApiResponses(value={
	    @ApiResponse(code=200,message="Rate Table Details Retrieved",response=List.class),
	    @ApiResponse(code=400,message="Resource Not Found", responseHeaders = @ResponseHeader(name = "X-Rack-Cache", description = "Explains whether or not a cache was used", response = List.class)),
	    @ApiResponse(code=500,message="Internal Server Error"),
	    @ApiResponse(code=404,message="Rate table not found")})
    @HystrixCommand(fallbackMethod = "fallbackall")
	@RequestMapping(value = "/rates", method = RequestMethod.GET)
	public List<ShippingRate> shippingRates() {
		return CalculateFacade.getRates();
	}
	
    @Path("/deliverydate")
    @ApiOperation(value="get shipping delivery date",response=LocalDate.class)
    @ApiResponses(value={
	    @ApiResponse(code=200,message="Delivery Date Retrieved",response=LocalDate.class),
	    @ApiResponse(code=400,message="Resource Not Found", responseHeaders = @ResponseHeader(name = "X-Rack-Cache", description = "Explains whether or not a cache was used", response = LocalDate.class)),
	    @ApiResponse(code=500,message="Internal Server Error"),
	    @ApiResponse(code=404,message="Delivery Date not found")})
    @HystrixCommand(fallbackMethod = "fallbackdelivery")
	@RequestMapping(value = "/deliverydate", method = RequestMethod.POST)
	public LocalDate computeOrderDeliveryDate(@Valid @RequestBody @ApiParam(value = "Order object", required = true) final Order order) {
		return CalculateFacade.calculateOrderDeliveryDate(order);
	}
	
    @Path("/total")
    @ApiOperation(value="get order total",response=Float.class)
    @ApiResponses(value={
	    @ApiResponse(code=200,message="Order Total Retrieved",response=Float.class),
	    @ApiResponse(code=400,message="Resource Not Found", responseHeaders = @ResponseHeader(name = "X-Rack-Cache", description = "Explains whether or not a cache was used", response = Float.class)),
	    @ApiResponse(code=500,message="Internal Server Error"),
	    @ApiResponse(code=404,message="Order Total not found")})
    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/total", method = RequestMethod.POST)
	public Float computeOrderTotal(@Valid @RequestBody @ApiParam(value = "Order object", required = true) final Order order) {
		return CalculateFacade.calculateOrderTotal(order);
	}
    
    public Double fallback() {
        return Double.parseDouble("0.01");
    }
    
    public String fallbackdelivery() {
        return String.valueOf(LocalDateTime.now().plusDays(3));
    }
    
    public List<ShippingRate> fallbackall() {
        return new ArrayList<ShippingRate>();
    }
}


