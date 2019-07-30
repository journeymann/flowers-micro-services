/**
 * 
 */
package com.flowers.microservice.tax.controller;

import java.util.Arrays;
import java.util.List;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.flowers.microservice.tax.model.TaxRate;
import com.flowers.microservice.tax.request.TaxRequest;
import com.flowers.microservice.tax.service.HealthIndicatorService;
import com.flowers.microservice.tax.service.TaxRateService;
import com.google.common.base.Optional;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  12/11/2018
 * @version 1.0
 *
 */
@RefreshScope
@PreAuthorize("hasAuthority('ROLE_TRUSTED_CLIENT')")
@RestController
public class TaxRateController {
	private static transient final Logger logger = LoggerFactory.getLogger(TaxRateController.class);

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
	private TaxRateService taxRateService;

	@Autowired
	private HealthIndicatorService healthIndicatorService;
	
    @Autowired
    private DiscoveryClient discoveryClient;
    
    @Autowired
    RestTemplate restTemplate;	
	
    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.discoveryClient.getInstances(applicationName);
    }	
	
    @HystrixCommand(fallbackMethod = "fallbackTaxRate")
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public TaxRate getTaxRateTypeById(@RequestParam(value="id", required=false) final String taxdataid,
			@RequestParam(value="zipcode", required=false) final String zipcode) {
		return Optional.of(zipcode).isPresent() ?  Optional.of(taxRateService.findTaxRateByZip(zipcode)).orNull() : 
			Optional.of(taxRateService.findTaxRateById(taxdataid)).orNull();
	}
    
    @HystrixCommand(fallbackMethod = "fallbackAmount")
	@RequestMapping(value = "/order", method = RequestMethod.GET)
	public Double getOrderTaxAmount(@RequestParam(value="id", defaultValue="10000") @Valid final String orderid) {
    	logger.debug("DEBUG Controller id: {}",orderid);
		return  Optional.of(taxRateService.calculateOrderTax(orderid)).orNull();
	}
    
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
    
    @HystrixCommand(fallbackMethod = "fallbackTaxRate")
	@RequestMapping(value = "/rate", method = RequestMethod.POST)
	public TaxRate getTaxRate(@RequestBody @Valid final TaxRequest taxdata) {
		return  Optional.of(taxRateService.findTaxRateByRequest(taxdata)).orNull();
	}
    
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


