/**
 * 
 */
package com.flowers.microservice.order.service;

import java.util.List;

import com.flowers.microservice.order.model.Order;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

public interface OrderService {
	
	Order createOrder(Order order);
	
	Order findOrderById(String orderid);

	List<Order> findAllOrderList();
	
	Order updateOrder(String orderid, Order order);
	
	void deleteOrder(String orderid);
}
