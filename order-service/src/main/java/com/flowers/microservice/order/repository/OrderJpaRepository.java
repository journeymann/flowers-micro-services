/**
 * 
 */
package com.flowers.microservice.order.repository;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.flowers.microservice.order.model.Order;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@Repository
@Component
public interface OrderJpaRepository extends CrudRepository<Object, String> {

	public default Order create(Order order){
		
		return order;
	}
	
	public default Order update(Order order){
		
		return order;
	}

	public default void delete(String orderid){
		
		//Do nothing
	}

	public default Order findOne(String orderid){
		
		Order ord = new Order();
		ord.setOrderId(orderid);
		ord.setorderNum("W919013801203GTH");
		ord.setOrderTotal(10091.89);
		ord.setDeliveryDate("01-01-2015");
		ord.setShippingDate("01-01-2017");
		ord.setTaxAmount(100.23);
		ord.setItemIdList(Arrays.asList("101092","1020920"));		
				
		return ord;
	}
	
	public default List<Order> readAll(){
		
		List<Order> list = new ArrayList<Order>();
		
		Order ord0 = new Order();
		Order ord1 = new Order();
		Order ord2 = new Order();
		
		ord1.setOrderId("1010294");
		ord1.setorderNum("W919013801203GTH");
		ord1.setOrderTotal(10091.89);
		ord1.setDeliveryDate("01-03-2015");
		ord1.setShippingDate("01-03-2017");
		ord1.setTaxAmount(102.23);
		ord1.setItemIdList(Arrays.asList("101094","1020921"));		
			
		ord2.setOrderId("1010292");
		ord2.setorderNum("W919013801203GTZ");
		ord2.setOrderTotal(10095.89);
		ord2.setDeliveryDate("01-01-2015");
		ord2.setShippingDate("01-01-2017");
		ord2.setTaxAmount(103.23);
		ord2.setItemIdList(Arrays.asList("101093","1020928"));		

		ord0.setOrderId("1010291");
		ord0.setorderNum("W919013801203GTU");
		ord0.setOrderTotal(10093.89);
		ord0.setDeliveryDate("01-02-2015");
		ord0.setShippingDate("01-02-2017");
		ord0.setTaxAmount(101.23);
		ord0.setItemIdList(Arrays.asList("101095","1020928"));		

		list = Arrays.asList(ord0, ord1, ord2);
		
		return list;
	}
}