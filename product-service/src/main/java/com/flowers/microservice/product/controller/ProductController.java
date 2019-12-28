/**
 * 
 */
package com.flowers.microservice.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.flowers.microservice.product.service.ProductService;
import com.flowers.microservice.beans.Product;
import com.flowers.microservice.product.health.HealthIndicatorService;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Contact;
import io.swagger.annotations.ExternalDocs;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
import io.swagger.annotations.ResponseHeader;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */

@SwaggerDefinition(
        info = @Info(
                description = "Gets product information and calculations",
                version = "V12.0.12",
                title = "The Product Service API",
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
@Api(value="/product",description="Product Information",produces ="application/json")
@Produces({"application/json"})
@Consumes({"application/json"})
@PreAuthorize("hasAuthority('ROLE_TRUSTED_CLIENT')")
@RefreshScope
public class ProductController {

    @Autowired
	private ProductService productService;
    
	@Autowired
	private HealthIndicatorService healthIndicatorService;    
        
    @Value(value = "${app.http.timeout}")
    private long timeout;
    
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

    @Value(value = "${eureka.instance.instance-id}")
    private String instanceId;

    @GetMapping("/service-instances/instanceid")
    public StringBuffer getEurekaStatus() {
        
        return new StringBuffer("instance id: " + instanceId);
    }  
    
    @Path("/product/create")
    @ApiOperation(value="create product",response=Product.class)
    @ApiResponses(value={
	    @ApiResponse(code=200,message="Product Created Success",response=Product.class),
	    @ApiResponse(code=400,message="Resource Not Found", responseHeaders = @ResponseHeader(name = "X-Rack-Cache", description = "Explains whether or not a cache was used", response = Product.class)),
	    @ApiResponse(code=500,message="Internal Server Error"),
	    @ApiResponse(code=404,message="Product Create Failed")})
    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/product/create", method = RequestMethod.POST)
	public Product createProduct(@PathVariable final String productid, @Valid @RequestBody final Product product) {
		return productService.updateProduct(productid, product);
	}
	
    @Path("/product/read/{productid}")
    @ApiOperation(value="read product details",response=Product.class)
    @ApiResponses(value={
	    @ApiResponse(code=200,message="Get Product Details",response=Product.class),
	    @ApiResponse(code=400,message="Resource Not Found", responseHeaders = @ResponseHeader(name = "X-Rack-Cache", description = "Explains whether or not a cache was used", response = Product.class)),
	    @ApiResponse(code=500,message="Internal Server Error"),
	    @ApiResponse(code=404,message="Product Not Found")})
    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/product/read/{productid}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable final String productid) {
		return productService.findProductById(productid);
	}
	
    @Path("/product/all")
    @ApiOperation(value="read all product details",response=List.class)
    @ApiResponses(value={
	    @ApiResponse(code=200,message="Get All Product Details",response=List.class),
	    @ApiResponse(code=400,message="Resource Not Found", responseHeaders = @ResponseHeader(name = "X-Rack-Cache", description = "Explains whether or not a cache was used", response = List.class)),
	    @ApiResponse(code=500,message="Internal Server Error"),
	    @ApiResponse(code=404,message="No Products Found")})
    @HystrixCommand(fallbackMethod = "fallbackAllProducts")
	@RequestMapping(value = "/product/all", method = RequestMethod.GET)
	public List<Product> getAllProducts() {
		return productService.findAllProductList();
	}
	
    @Path("/product/update/{productid}")
    @ApiOperation(value="update product details",response=Product.class)
    @ApiResponses(value={
	    @ApiResponse(code=200,message="Update Product Details",response=Product.class),
	    @ApiResponse(code=400,message="Resource Not Found", responseHeaders = @ResponseHeader(name = "X-Rack-Cache", description = "Explains whether or not a cache was used", response = Product.class)),
	    @ApiResponse(code=500,message="Internal Server Error"),
	    @ApiResponse(code=404,message="Product Not Found")})
    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/product/update/{productid}", method = RequestMethod.POST)
	public Product updateProduct(@PathVariable final String productid, @Valid @RequestBody final Product product) {
		return productService.updateProduct(productid, product);
	}
	
    @Path("/product/delete/{productid}")
    @ApiOperation(value="delete product")
    @ApiResponses(value={
	    @ApiResponse(code=200,message="Delete Product"),
	    @ApiResponse(code=400,message="Resource Not Found", responseHeaders = @ResponseHeader(name = "X-Rack-Cache", description = "Explains whether or not a cache was used")),
	    @ApiResponse(code=500,message="Internal Server Error"),
	    @ApiResponse(code=404,message="Product Not Found")})
	@RequestMapping(value = "/product/delete/{productid}", method = RequestMethod.GET)
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