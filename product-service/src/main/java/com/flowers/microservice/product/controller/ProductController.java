/**
 * 
 */
package com.flowers.microservice.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.flowers.microservice.product.domain.Product;
import com.flowers.microservice.product.service.ProductService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */

@RestController
@PreAuthorize("hasAuthority('ROLE_TRUSTED_CLIENT')")
@RequestMapping(path = "/product")
public class ProductController {

    @Autowired
	private ProductService productService;
        
    @Value(value = "${http.timeout:5}")
    private long timeout;

    @Autowired
    private DiscoveryClient discoveryClient;

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
    
    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/product/create}", method = RequestMethod.POST)
	public Product createProduct(@PathVariable final String productid, @Valid @RequestBody final Product product) {
		return productService.updateProduct(productid, product);
	}
	
    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/product/read/{productid}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable final String productid) {
		return productService.findProductById(productid);
	}
	
    @HystrixCommand(fallbackMethod = "fallbackAllProducts")
	@RequestMapping(value = "/product/all}", method = RequestMethod.GET)
	public List<Product> getAllProducts() {
		return productService.findAllProductList();
	}
	
    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/product/update/{productid}", method = RequestMethod.POST)
	public Product updateProduct(@PathVariable final String productid, @Valid @RequestBody final Product product) {
		return productService.updateProduct(productid, product);
	}
	
	@RequestMapping(value = "/product/delete/{productid}", method = RequestMethod.PUT)
	public void deleteProduct(@PathVariable final String productid) {
		productService.deleteProduct(productid);
	}
	
    public Product fallback() {
        return new Product();
    }
    
    public Collection<Product> fallbackAllProducts() {
        return new ArrayList<Product>();
    }
   
}