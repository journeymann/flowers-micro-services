/**
 * 
 */
package com.flowers.microservice.order.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flowers.microservice.order.model.Order;
import com.flowers.microservice.order.repository.OrderJpaRepository;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderJpaRepository repository;


	public Order createOrder(Order order){
		
		return repository.save(order);
	};
	
	public Order findOrderById(String orderid){
		
		return (Order) repository.findOne(orderid);
	};

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Order> findAllOrderList(){
		
		List<Order> list = new ArrayList<Order>();
		
		copyListElements((List)repository.findAll(),(Supplier<Collection<Order>>)list);
		
		return list;
	}
	
	public Order updateOrder(String orderid, Order order){
		
		return repository.findOne(orderid) != null? repository.save(order) : repository.update(order);
	};
	
	public void deleteOrder(String orderid){
		
		repository.delete(orderid);
	};
	
    public static <T> void copyListElements(final List<T> list, Supplier<Collection<T>> targetCollection) {
        list.forEach(targetCollection.get()::add);
    }
}
	
