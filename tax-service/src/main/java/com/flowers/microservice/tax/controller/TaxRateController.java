/**
 * 
 */
package com.flowers.microservice.tax.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.flowers.microservice.beans.TaxRate;
import com.flowers.microservice.common.AbstractController;
import com.flowers.microservice.common.LoggingHelper;
import com.flowers.microservice.tax.health.HealthIndicatorService;
import com.flowers.microservice.tax.request.TaxRequest;
import com.flowers.microservice.tax.service.TaxRateService;
import com.google.common.base.Optional;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Contact;
import io.swagger.annotations.ExternalDocs;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.ResponseHeader;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  12/11/2018
 * @version 1.0
 *
 */

@SwaggerDefinition(
        info = @Info(
                description = "Gets shipping information and calculates tax rates",
                version = "V12.0.12",
                title = "The Taxation API",
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

@Api(value="/tax",description="Tax Rates",produces ="application/json")
@Produces({"application/json"})
@Consumes({"application/json"})
//@PreAuthorize("hasAuthority('ROLE_TRUSTED_CLIENT')")
public class TaxRateController extends AbstractController{
	private static final Logger logger = LoggingHelper.getLogger(TaxRateController.class);

	@Value("${flowers.tax.service.default.rate}")
	private Double defaultRate;
	@Value("${flowers.tax.service.default.state}")
	private String defaultState;
	@Value("${flowers.tax.service.default.county}")
	private String defaultCounty;
	@Value("${flowers.tax.service.default.zipcode}")
	private String defaultZipcode;
	@Value("${flowers.tax.service.default.id}")
	private String defaultId;
	@Value("${flowers.tax.service.default.override}")
	private Boolean defaultTaxOverride;
	
    @Autowired
    private EurekaClient eurekaClient;
    
	@Autowired
	private TaxRateService taxRateService;

	@Autowired
	private HealthIndicatorService healthIndicatorService;

    @Autowired
    RestTemplate restTemplate;	
	
    @RequestMapping("/service-instances/{applicationName}")
    public List<InstanceInfo> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.eurekaClient.getApplication(applicationName).getInstances();
    }  

    @Value("${eureka.instance.instance-id}")
    private String instanceId;
    
    @Value("${app.info.description}")
    private String serviceInfo;    
    
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
    	
    @Path("/read")
    @ApiOperation(value="get rate by id",response=TaxRate.class)
    @ApiResponses(value={
	    @ApiResponse(code=200,message="Tax Total Retrieved",response=TaxRate.class),
	    @ApiResponse(code=400,message="Resource Not Found", responseHeaders = @ResponseHeader(name = "X-Rack-Cache", description = "Explains whether or not a cache was used", response = TaxRate.class)),
	    @ApiResponse(code=500,message="Internal Server Error"),
	    @ApiResponse(code=404,message="Order Total not found")})
    @HystrixCommand(fallbackMethod = "fallbackTaxRate")
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public TaxRate getTaxRateTypeById(@RequestParam(value="id", required=false) final String taxdataid,
			@RequestParam(value="zipcode", required=false) final String zipcode) {
		return Optional.of(zipcode).isPresent() ?  Optional.of(taxRateService.findTaxRateByZip(zipcode)).orNull() : 
			Optional.of(taxRateService.findTaxRateById(taxdataid)).orNull();
	}
    
    @Path("/order")
    @ApiOperation(value="get order tax amount",response=Double.class)
    @ApiResponses(value={
	    @ApiResponse(code=200,message="Tax Total Retrieved",response=Float.class),
	    @ApiResponse(code=400,message="Resource Not Found", responseHeaders = @ResponseHeader(name = "X-Rack-Cache", description = "Explains whether or not a cache was used", response = Double.class)),
	    @ApiResponse(code=500,message="Internal Server Error"),
	    @ApiResponse(code=404,message="Order Total not found")})
    @HystrixCommand(fallbackMethod = "fallbackAmount")
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public Double getOrderTaxAmount(@RequestParam(value="id", defaultValue="10000") @Valid final String orderid) {
    	logger.debug("DEBUG Controller id: {}",orderid);
		return  Optional.of(taxRateService.calculateOrderTax(orderid)).orNull();
	}
    
    @Path("/product")
    @ApiOperation(value="get item tax amount",response=Double.class)
    @ApiResponses(value={
	    @ApiResponse(code=200,message="Item Tax Total Retrieved",response=Double.class),
	    @ApiResponse(code=400,message="Resource Not Found", responseHeaders = @ResponseHeader(name = "X-Rack-Cache", description = "Explains whether or not a cache was used", response = Double.class)),
	    @ApiResponse(code=500,message="Internal Server Error"),
	    @ApiResponse(code=404,message="Item Tax not found")})
    @HystrixCommand(fallbackMethod = "fallbackAmount")
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public Double getItemTaxAmount(@RequestParam(value="id", defaultValue="10000") @Valid final String itemid) {
    	logger.debug("DEBUG Controller id: {}",itemid);
		return  Optional.of(taxRateService.calculateItemTax(itemid)).orNull();
	}
	
	@RequestMapping(value = "/health",  method = RequestMethod.GET)
	public InstanceStatus health() {
		return healthIndicatorService.health();
	}
	
	@RequestMapping(value = "/info",  method = RequestMethod.GET)
	public String information() {
		return String.format("Service description: %s. Health status %s", serviceInfo,  healthIndicatorService.health());
	}	
    
    @Path("/rate")
    @ApiOperation(value="get tax rate information",response=TaxRate.class)
    @ApiResponses(value={
	    @ApiResponse(code=200,message="Tax Rate Total Retrieved",response=TaxRate.class),
	    @ApiResponse(code=400,message="Resource Not Found", responseHeaders = @ResponseHeader(name = "X-Rack-Cache", description = "Explains whether or not a cache was used", response = TaxRate.class)),
	    @ApiResponse(code=500,message="Internal Server Error"),
	    @ApiResponse(code=404,message="Tax Rate not found")})
    @HystrixCommand(fallbackMethod = "fallbackTaxRate")
	@RequestMapping(value = "/rate", method = RequestMethod.POST)
	public TaxRate getTaxRate(@RequestBody @Valid final TaxRequest taxdata) {
		return  Optional.of(taxRateService.findTaxRateByRequest(taxdata)).orNull();
	}
    
    @Path("/all")
    @ApiOperation(value="get all tax rates",response=List.class)
    @ApiResponses(value={
	    @ApiResponse(code=200,message="Tax Rates Retrieved",response=List.class),
	    @ApiResponse(code=400,message="Resource Not Found", responseHeaders = @ResponseHeader(name = "X-Rack-Cache", description = "Explains whether or not a cache was used", response = List.class)),
	    @ApiResponse(code=500,message="Internal Server Error"),
	    @ApiResponse(code=404,message="Tax Rates not found")})
    @HystrixCommand(fallbackMethod = "fallbackAllTaxRates")
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<TaxRate> getAllTaxRates() {
		return Optional.of(taxRateService.findAllTaxRates()).orNull();
	}
	
    public TaxRate fallbackTaxRate(String taxdataid) {
        return new TaxRate(taxdataid,defaultState, defaultCounty,defaultZipcode,defaultRate, defaultTaxOverride);
    }

    public TaxRate fallbackTaxRate(String taxdataid, String zipcode) {
        return new TaxRate(taxdataid,defaultState, defaultCounty,defaultZipcode,defaultRate, defaultTaxOverride);
    }
    
    public Double fallbackAmount(String entityid) {
        return defaultRate;
    }
    
    public List<TaxRate> fallbackAllTaxRates() {
        return Arrays.asList(new TaxRate(defaultId, defaultState, defaultCounty,defaultZipcode,defaultRate, defaultTaxOverride));
    }
}


