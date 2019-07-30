/**
 * 
 */
package com.flowers.microservice.order.service;

import java.util.List;

import com.flowers.microservice.order.domain.Order;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */

public interface OrderService {
	
	Order createOrder(Order order);
	
	Order findOrderById(String orderid);

	List<Order> findAllOrderList();
	
	Order updateOrder(String orderid, Order order);
	
	void deleteOrder(String orderid);

	List<Order> findAllOrderListPaginated(int pageNumber, int pageSize);

	Order findOneById(String id);

	List<Order> findById(String id);

	Order updateOne(Order order);
}
