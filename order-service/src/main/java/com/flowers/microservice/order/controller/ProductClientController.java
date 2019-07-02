/**
 * 
 */
package com.flowers.microservice.order.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.flowers.microservice.order.model.Product;
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
public class ProductClientController {
	
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EurekaClient eurekaClient;
    @Value("${service.productsearch.serviceId}")
    private String productSearchServiceId;
    
    @HystrixCommand(fallbackMethod = "fallbackProdById")
    @RequestMapping("/order/product/{productId}")
    public Product find(@PathVariable Long productId) {
        Application application = eurekaClient.getApplication(productSearchServiceId);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "product/read/" + productId;
        System.out.printf("URL: %s\n",url);
        Product prod = restTemplate.getForObject(url, Product.class);
        System.out.printf("RESPONSE: %s\n",prod);
        return prod;
    }
        
    @HystrixCommand(fallbackMethod = "fallbackAllProducts")
    @RequestMapping("/order/product/all")
    public Collection <Product> findProducts() {
        Application application = eurekaClient.getApplication(productSearchServiceId);
        InstanceInfo instanceInfo = application.getInstances().get(0);
        String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + "product/all";
        System.out.printf("URL: %s\n", url);
        
		Collection<Product> list =  restTemplate.getForObject(url, ProductCollection.class);
        System.out.printf("RESPONSE: %s\n", list);
        return list;
    }
    
    public Product fallbackProdById() {
        return new Product();
    }
    
    public Collection<Product> fallbackAllProducts() {
        return new ArrayList<Product>();
    }    
	private final class ProductCollection extends ArrayList<Product>{
    	private static final long serialVersionUID = 7056106857753243257L;

    }
}


