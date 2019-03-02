/**
 * 
 */
package com.flowers.microservice.order.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.flowers.microservice.order.model.Order;
import com.flowers.microservice.order.service.OrderService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(value = "/create}", method = RequestMethod.POST)
	public Order createOrder(@Valid @RequestBody final Order order) {
		return orderService.createOrder(order);
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


