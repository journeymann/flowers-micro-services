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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.TypeReferences;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
import com.flowers.microservice.order.health.HealthIndicatorService;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import java.util.concurrent.ExecutionException;
import java.io.IOException;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
@RestController
@ConfigurationProperties
public class OrderController{
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private AppConfigProperties config;
    
    @Autowired
	private OrderService orderService;
    
    @Autowired
    private AsyncGetService asyncGetService;
    
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

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @GetMapping("/service-instances/instanceid")
    public StringBuffer getEurekaStatus() {
        
        return new StringBuffer("instance id: " + instanceId);
    }  
    
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
	
    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/read/{orderid}", method = RequestMethod.GET)
	public OrderItem getOrder(@PathVariable final String orderid) {
		return orderService.findOrderById(orderid);
	}
	
    @HystrixCommand(fallbackMethod = "fallbackAllOrders")
	@RequestMapping(value = "/all}", method = RequestMethod.GET)
	public List<OrderItem> getAllOrders() {
		return orderService.findAllOrderList();
	}
	
    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/update/{orderid}", method = RequestMethod.POST)
	public OrderItem updateOrder(@PathVariable final String orderid, @Valid @RequestBody final OrderItem orderItem) {
		return orderService.updateOrder(orderid, orderItem);
	}
	
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


