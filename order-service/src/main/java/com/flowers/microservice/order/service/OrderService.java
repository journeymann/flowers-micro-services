/**
 * 
 */
package com.flowers.microservice.order.service;

import java.util.List;

import com.flowers.microservice.beans.OrderItem;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */

public interface OrderService {
	
	OrderItem createOrder(OrderItem orderItem);
	
	OrderItem findOrderById(String orderid);

	List<OrderItem> findAllOrderList();
	
	OrderItem updateOrder(String orderid, OrderItem orderItem);
	
	void deleteOrder(String orderid);

	List<OrderItem> findAllOrderListPaginated(int pageNumber, int pageSize);

	OrderItem findOneById(String id);

	List<OrderItem> findById(String id);

	OrderItem updateOne(OrderItem orderItem);
}
