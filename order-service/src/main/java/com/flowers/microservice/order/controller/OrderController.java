/**
 * 
 */
package com.flowers.microservice.order.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.validation.Valid;
import javax.ws.rs.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.TypeReferences;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.flowers.microservice.beans.OrderItem;
import com.flowers.microservice.order.config.AppConfigProperties;
import com.flowers.microservice.beans.billing.CreditCard;
import com.flowers.microservice.beans.contact.Address;
import com.flowers.microservice.beans.contact.Customer;
import com.flowers.microservice.beans.Item;
import com.flowers.microservice.beans.Shipment;
import com.flowers.microservice.order.exception.InvalidOrderException;
import com.flowers.microservice.order.exception.PaymentDeclinedException;
import com.flowers.microservice.order.facade.OrderFacade;
import com.flowers.microservice.order.proxy.AsyncGetService;
import com.flowers.microservice.order.resource.NewOrderResource;
import com.flowers.microservice.order.resource.PaymentRequest;
import com.flowers.microservice.order.resource.PaymentResponse;
import com.flowers.microservice.order.service.OrderService;
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
import io.swagger.models.Response;

import java.util.concurrent.ExecutionException;
import java.io.IOException;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
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

@Api(value="/order",description="Order Calculations",produces ="application/json")
//@PreAuthorize("hasAuthority('ROLE_TRUSTED_CLIENT')")
public class OrderController{
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private AppConfigProperties config;
    
    @Autowired
	private OrderService orderService;
    
    @Autowired
    private AsyncGetService asyncGetService;
  
    @Value(value = "${app.http.timeout}")
    private long timeout;
    
    @Path("/create")
    @ApiOperation(value="create order service",response=OrderItem.class)
    @ApiResponses(value={
	    @ApiResponse(code=200,message="Order Created Success",response=String.class),
	    @ApiResponse(code=400,message="Internal Server Error", responseHeaders = @ResponseHeader(name = "X-Rack-Cache", description = "Explains whether or not a cache was used", response = OrderItem.class)),
	    @ApiResponse(code=500,message="Internal Server Error"),
	    @ApiResponse(code=404,message="Unable to create Order")})    
    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/create}", method = RequestMethod.POST)
	public OrderItem createOrder(@Valid @RequestBody final NewOrderResource orderitem) {
    	try {
    		
            if (orderitem.address == null || orderitem.customer == null || orderitem.card == null || orderitem.items == null) {
                throw new InvalidOrderException("Invalid order request. OrderItem requires customer, address, card and items.");
            }

            LOG.debug("Starting calls");
            Future<Resource<Address>> addressFuture = asyncGetService.getResource(orderitem.address, new TypeReferences
                    .ResourceType<Address>() {
            });
            Future<Resource<Customer>> customerFuture = asyncGetService.getResource(orderitem.customer, new TypeReferences
                    .ResourceType<Customer>() {
            });
            Future<Resource<CreditCard>> cardFuture = asyncGetService.getResource(orderitem.card, new TypeReferences
                    .ResourceType<CreditCard>() {
            });
            Future<List<Item>> itemsFuture = asyncGetService.getDataList(orderitem.items, new
                    ParameterizedTypeReference<List<Item>>() {
            });
            LOG.debug("End of calls.");

            Float taxes = OrderFacade.calculateTax(itemsFuture.get(timeout, TimeUnit.MILLISECONDS));
            Float amount = OrderFacade.calculateTotal(itemsFuture.get(timeout, TimeUnit.MILLISECONDS));

            // Call payment service to make sure they've paid
            PaymentRequest paymentRequest = new PaymentRequest(
                    addressFuture.get(timeout, TimeUnit.MILLISECONDS).getContent(),
                    cardFuture.get(timeout, TimeUnit.MILLISECONDS).getContent(),
                    customerFuture.get(timeout, TimeUnit.MILLISECONDS).getContent(),
                    amount);
            LOG.info("Sending payment request: " + paymentRequest);
            Future<PaymentResponse> paymentFuture = asyncGetService.postResource(
                    config.getPaymentUri(),
                    paymentRequest,
                    new ParameterizedTypeReference<PaymentResponse>() {
                    });
            PaymentResponse paymentResponse = paymentFuture.get(timeout, TimeUnit.MILLISECONDS);
            LOG.info("Received payment response: " + paymentResponse);
            if (paymentResponse == null) {
                throw new PaymentDeclinedException("Unable to parse authorisation packet");
            }
            if (!paymentResponse.isAuthorised()) {
                throw new PaymentDeclinedException(paymentResponse.getMessage());
            }

            // Ship
            String customerId = OrderFacade.parseId(customerFuture.get(timeout, TimeUnit.MILLISECONDS).getId().getHref());
            Future<Shipment> shipmentFuture = asyncGetService.postResource(config.getShippingUri(), new Shipment
                    (customerId), new ParameterizedTypeReference<Shipment>() {
            });
            
            OrderItem orderItem = new OrderItem(
                    null,
                    customerId,
                    customerFuture.get(timeout, TimeUnit.MILLISECONDS).getContent(),
                    addressFuture.get(timeout, TimeUnit.MILLISECONDS).getContent(),
                    cardFuture.get(timeout, TimeUnit.MILLISECONDS).getContent(),
                    itemsFuture.get(timeout, TimeUnit.MILLISECONDS),
                    shipmentFuture.get(timeout, TimeUnit.MILLISECONDS),
                    amount,taxes);
            LOG.debug("Received data: " + orderItem.toString());

            orderService.createOrder(orderItem);;
            LOG.debug("Saved order: " + orderItem);

            return orderService.createOrder(orderItem);
        } catch (TimeoutException e) {
            throw new IllegalStateException("Unable to create order due to timeout from one of the services.", e);
        } catch (InterruptedException | IOException | ExecutionException e) {
            throw new IllegalStateException("Unable to create order due to unspecified IO error.", e);
        }
    			
	}
	
    @Path("/read/{orderid}")
    @ApiOperation(value="get order information by id",response=OrderItem.class)
    @ApiResponses(value={
	    @ApiResponse(code=200,message="Order Details Retrieved",response=String.class),
	    @ApiResponse(code=400,message="Resource Not Found", responseHeaders = @ResponseHeader(name = "X-Rack-Cache", description = "Explains whether or not a cache was used", response = OrderItem.class)),
	    @ApiResponse(code=500,message="Internal Server Error"),
	    @ApiResponse(code=404,message="Order not found")})
    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/read/{orderid}", method = RequestMethod.GET)
	public OrderItem getOrder(@PathVariable final String orderid) {
		return orderService.findOrderById(orderid);
	}
	
    @Path("/all")
    @ApiOperation(value="get all order information",response=Response.class)
    @ApiResponses(value={
	    @ApiResponse(code=200,message="All Order Details Retrieved",response=String.class),
	    @ApiResponse(code=400,message="Resource Not Found", responseHeaders = @ResponseHeader(name = "X-Rack-Cache", description = "Explains whether or not a cache was used", response = Response.class)),
	    @ApiResponse(code=500,message="Internal Server Error"),
	    @ApiResponse(code=404,message="All Order not found")})
    @HystrixCommand(fallbackMethod = "fallbackAllOrders")
	@RequestMapping(value = "/all}", method = RequestMethod.GET)
	public List<OrderItem> getAllOrders() {
		return orderService.findAllOrderList();
	}
	
    @Path("/update/{orderid}")
    @ApiOperation(value="update order information",response=OrderItem.class)
    @ApiResponses(value={
	    @ApiResponse(code=200,message="Update Order Success",response=String.class),
	    @ApiResponse(code=400,message="Resource Not Found", responseHeaders = @ResponseHeader(name = "X-Rack-Cache", description = "Explains whether or not a cache was used", response = OrderItem.class)),
	    @ApiResponse(code=500,message="Internal Server Error"),
	    @ApiResponse(code=404,message="Order update Failed")})
    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/update/{orderid}", method = RequestMethod.POST)
	public OrderItem updateOrder(@PathVariable final String orderid, @Valid @RequestBody final OrderItem orderItem) {
		return orderService.updateOrder(orderid, orderItem);
	}
	
    @Path("/delete/{orderid}")
    @ApiOperation(value="delete order information",response=OrderItem.class)
    @ApiResponses(value={
	    @ApiResponse(code=200,message="Delete Order Success",response=String.class),
	    @ApiResponse(code=400,message="Resource Not Found", responseHeaders = @ResponseHeader(name = "X-Rack-Cache", description = "Explains whether or not a cache was used", response = OrderItem.class)),
	    @ApiResponse(code=500,message="Internal Server Error"),
	    @ApiResponse(code=404,message="Order delete Failed")})
	@RequestMapping(value = "/delete/{orderid}", method = RequestMethod.PUT)
	public void deleteOrder(@PathVariable final String orderid) {
		orderService.deleteOrder(orderid);
	}
	
    public OrderItem fallback() {
        return new OrderItem();
    }
    
    public Collection<OrderItem> fallbackAllOrders() {
        return new ArrayList<OrderItem>();
    }
 
}


