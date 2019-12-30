/**
 * 
 */
package com.flowers.microservice.inventory.controller;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.flowers.microservice.beans.Inventory;
import com.flowers.microservice.common.AbstractController;
import com.flowers.microservice.inventory.service.InventoryService;
import com.netflix.appinfo.InstanceInfo;
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

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */

@SwaggerDefinition(
        info = @Info(
                description = "Gets Inventory information",
                version = "V12.0.12",
                title = "The Inventory API",
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

@Api(value="/order",description="Order Calculations",produces ="application/json")
@Produces({"application/json"})
@Consumes({"application/json"})
@PreAuthorize("hasAuthority('ROLE_TRUSTED_CLIENT')")
public class InventoryController extends AbstractController{
    
    @Autowired
	private InventoryService inventoryService;
        
    @Value(value = "${app.http.timeout}")
    private long timeout;

    @RequestMapping("/service-instances/{applicationName}")
    public List<InstanceInfo> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.eurekaClient.getApplication(applicationName).getInstances();
    }    
    
    @Path("/inventory/read/{productid}")
    @ApiOperation(value="get inventory information for product id",response=Inventory.class)
    @ApiResponses(value={
	    @ApiResponse(code=200,message="Inventory Details Retrieved",response=Inventory.class),
	    @ApiResponse(code=400,message="Resource Not Found", responseHeaders = @ResponseHeader(name = "X-Rack-Cache", description = "Explains whether or not a cache was used", response = Inventory.class)),
	    @ApiResponse(code=500,message="Internal Server Error"),
	    @ApiResponse(code=404,message="Inventory not found")})
    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/inventory/read/{productid}", method = RequestMethod.GET)
	public Inventory getProduct(@PathVariable final String productid) {
		return inventoryService.findProductCountById(productid);
	}
	
    @Path("/inventory/update/{productid}")
    @ApiOperation(value="updates inventory information for product id",response=Inventory.class)
    @ApiResponses(value={
	    @ApiResponse(code=200,message="Inventory Details Retrieved",response=Inventory.class),
	    @ApiResponse(code=400,message="Resource Not Found", responseHeaders = @ResponseHeader(name = "X-Rack-Cache", description = "Explains whether or not a cache was used", response = Inventory.class)),
	    @ApiResponse(code=500,message="Internal Server Error"),
	    @ApiResponse(code=404,message="Inventory not found")})
    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/inventory/update/{productid}", method = RequestMethod.POST)
	public Inventory updateProduct(@PathVariable final String productid, @PathVariable final Long count) {
		return inventoryService.updateProductCount(productid, count);
	}
	
    public Inventory fallback() {
        return new Inventory();
    }
  
}