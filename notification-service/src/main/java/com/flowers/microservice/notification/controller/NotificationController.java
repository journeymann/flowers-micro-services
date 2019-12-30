/**
 * 
 */
package com.flowers.microservice.notification.controller;


import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.flowers.microservice.common.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.Contact;
import io.swagger.annotations.ExternalDocs;
import io.swagger.annotations.Info;
import io.swagger.annotations.License;
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
                description = "Gets Notifications",
                version = "V12.0.12",
                title = "The Notification API",
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

@Api(value="/notification",description="Order Calculations",produces ="application/json")
@Produces({"application/json"})
@Consumes({"application/json"})
@PreAuthorize("hasAuthority('ROLE_TRUSTED_CLIENT')")
public class NotificationController extends AbstractController{
	    
		
		@RequestMapping(value = "/info",  method = RequestMethod.GET)
		public String information() {
			return String.format("Service description: %s. Health status %s", serviceInfo,  healthIndicatorService.health());
		}	
	    
}