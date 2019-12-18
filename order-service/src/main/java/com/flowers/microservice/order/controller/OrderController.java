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
import com.flowers.microservice.order.config.AppConfigProperties;
import com.flowers.microservice.order.domain.Address;
import com.flowers.microservice.order.domain.billing.Card;
import com.flowers.microservice.order.domain.Customer;
import com.flowers.microservice.order.domain.Item;
import com.flowers.microservice.order.domain.Order;
import com.flowers.microservice.order.domain.Shipment;
import com.flowers.microservice.order.exception.InvalidOrderException;
import com.flowers.microservice.order.exception.PaymentDeclinedException;
import com.flowers.microservice.order.facade.OrderFacade;
import com.flowers.microservice.order.proxy.AsyncGetService;
import com.flowers.microservice.order.resource.NewOrderResource;
import com.flowers.microservice.order.resource.PaymentRequest;
import com.flowers.microservice.order.resource.PaymentResponse;
import com.flowers.microservice.order.service.OrderService;
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
public class OrderController{
	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private AppConfigProperties config;
    
    @Autowired
	private OrderService orderService;
    
    @Autowired
    private AsyncGetService asyncGetService;
    
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
	@RequestMapping(value = "/create}", method = RequestMethod.POST)
	public Order createOrder(@Valid @RequestBody final NewOrderResource orderitem) {
    	try {
    		
            if (orderitem.address == null || orderitem.customer == null || orderitem.card == null || orderitem.items == null) {
                throw new InvalidOrderException("Invalid order request. Order requires customer, address, card and items.");
            }

            LOG.debug("Starting calls");
            Future<Resource<Address>> addressFuture = asyncGetService.getResource(orderitem.address, new TypeReferences
                    .ResourceType<Address>() {
            });
            Future<Resource<Customer>> customerFuture = asyncGetService.getResource(orderitem.customer, new TypeReferences
                    .ResourceType<Customer>() {
            });
            Future<Resource<Card>> cardFuture = asyncGetService.getResource(orderitem.card, new TypeReferences
                    .ResourceType<Card>() {
            });
            Future<List<Item>> itemsFuture = asyncGetService.getDataList(orderitem.items, new
                    ParameterizedTypeReference<List<Item>>() {
            });
            LOG.debug("End of calls.");

            float taxes = OrderFacade.calculateTax(itemsFuture.get(timeout, TimeUnit.SECONDS));
            float amount = OrderFacade.calculateTotal(itemsFuture.get(timeout, TimeUnit.SECONDS));

            // Call payment service to make sure they've paid
            PaymentRequest paymentRequest = new PaymentRequest(
                    addressFuture.get(timeout, TimeUnit.SECONDS).getContent(),
                    cardFuture.get(timeout, TimeUnit.SECONDS).getContent(),
                    customerFuture.get(timeout, TimeUnit.SECONDS).getContent(),
                    amount);
            LOG.info("Sending payment request: " + paymentRequest);
            Future<PaymentResponse> paymentFuture = asyncGetService.postResource(
                    config.getPaymentUri(),
                    paymentRequest,
                    new ParameterizedTypeReference<PaymentResponse>() {
                    });
            PaymentResponse paymentResponse = paymentFuture.get(timeout, TimeUnit.SECONDS);
            LOG.info("Received payment response: " + paymentResponse);
            if (paymentResponse == null) {
                throw new PaymentDeclinedException("Unable to parse authorisation packet");
            }
            if (!paymentResponse.isAuthorised()) {
                throw new PaymentDeclinedException(paymentResponse.getMessage());
            }

            // Ship
            String customerId = OrderFacade.parseId(customerFuture.get(timeout, TimeUnit.SECONDS).getId().getHref());
            Future<Shipment> shipmentFuture = asyncGetService.postResource(config.getShippingUri(), new Shipment
                    (customerId), new ParameterizedTypeReference<Shipment>() {
            });
            
            Order order = new Order(
                    null,
                    customerId,
                    customerFuture.get(timeout, TimeUnit.SECONDS).getContent(),
                    addressFuture.get(timeout, TimeUnit.SECONDS).getContent(),
                    cardFuture.get(timeout, TimeUnit.SECONDS).getContent(),
                    itemsFuture.get(timeout, TimeUnit.SECONDS),
                    shipmentFuture.get(timeout, TimeUnit.SECONDS),
                    amount,taxes);
            LOG.debug("Received data: " + order.toString());

            orderService.createOrder(order);;
            LOG.debug("Saved order: " + order);

            return orderService.createOrder(order);
        } catch (TimeoutException e) {
            throw new IllegalStateException("Unable to create order due to timeout from one of the services.", e);
        } catch (InterruptedException | IOException | ExecutionException e) {
            throw new IllegalStateException("Unable to create order due to unspecified IO error.", e);
        }
    			
	}
	
    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/read/{orderid}", method = RequestMethod.GET)
	public Order getOrder(@PathVariable final String orderid) {
		return orderService.findOrderById(orderid);
	}
	
    @HystrixCommand(fallbackMethod = "fallbackAllOrders")
	@RequestMapping(value = "/all}", method = RequestMethod.GET)
	public List<Order> getAllOrders() {
		return orderService.findAllOrderList();
	}
	
    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/update/{orderid}", method = RequestMethod.POST)
	public Order updateOrder(@PathVariable final String orderid, @Valid @RequestBody final Order order) {
		return orderService.updateOrder(orderid, order);
	}
	
	@RequestMapping(value = "/delete/{orderid}", method = RequestMethod.PUT)
	public void deleteOrder(@PathVariable final String orderid) {
		orderService.deleteOrder(orderid);
	}
	
    public Order fallback() {
        return new Order();
    }
    
    public Collection<Order> fallbackAllOrders() {
        return new ArrayList<Order>();
    }
 
}


