/**
 * 
 */
package com.flowers.microservice.product.controller;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.flowers.microservice.product.domain.Order;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@RefreshScope
@RestController
public class OrderClientController {
	
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EurekaClient eurekaClient;
    @Value("${service.ordersearch.serviceId}")
    private String orderSearchServiceId;
    
    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping("/product/order/{orderId}")
    public Order find(@PathVariable Long productId) {
        Application application = eurekaClient.getApplication(orderSearchServiceId);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "order/read/" + productId;
        System.out.printf("URL: %s\n",url);
        Order prod = restTemplate.getForObject(url, Order.class);
        System.out.printf("RESPONSE: %s\n",prod);
        return prod;
    }
    
    @HystrixCommand(fallbackMethod = "fallbackAllOrders")
    @RequestMapping("/order/product/all")
    public Collection <Order> findOrders() {
        Application application = eurekaClient.getApplication(orderSearchServiceId);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "order/all";
        System.out.printf("URL: %s\n", url);
        
		Collection<Order> list =  restTemplate.getForObject(url, OrderCollection.class);
        System.out.printf("RESPONSE: %s\n", list);
        return list;
    }
    
    public Order fallback() {
        return new Order();
    }
    
    public Collection<Order> fallbackAllOrders() {
        return new ArrayList<Order>();
    }
    
	private final class OrderCollection extends ArrayList<Order>{
    	private static final long serialVersionUID = 7056106857753243257L;

    }
}
