/**
 * 
 */
package com.flowers.microservice.order.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.flowers.microservice.order.domain.Address;
import com.flowers.microservice.order.domain.Card;
import com.flowers.microservice.order.domain.Customer;
import com.flowers.microservice.order.domain.Item;
import com.flowers.microservice.order.domain.Order;
import com.flowers.microservice.order.domain.Product;
import com.flowers.microservice.order.domain.Shipment;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */

@Component
public class OrderServiceImpl implements OrderService {

    private final MongoTemplate mongoTemplate;
    
    @Autowired
    public OrderServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.init();
    }
    
	public Order createOrder(Order order) {
		return mongoTemplate.save(order);
	};
	
	@Override
	public Order findOneById(String id) {
	   Query query = new Query();
	   query.addCriteria(Criteria.where("name").is(id));
	   return mongoTemplate.findOne(query, Order.class);
	}
	
	@Override
	public List<Order> findById(String id) {
	   Query query = new Query();
	   query.addCriteria(Criteria.where("name").is(id));
	   return mongoTemplate.find(query, Order.class);
	}

	public List<Order> findAllOrderList(){
		
		return mongoTemplate.findAll(Order.class);
	}
	
	@Override
	public List<Order> findAllOrderListPaginated(int pageNumber, int pageSize) {
	   Query query = new Query();
	   query.skip(pageNumber * pageSize);
	   query.limit(pageSize);
	   return mongoTemplate.find(query, Order.class);
	}
	
	@Override
	public Order updateOne(Order order) {
	   mongoTemplate.save(order);
	   return order;
	}
	
	public void deleteOrder(Order order){
		
		mongoTemplate.remove(order);
	};
	
    public static <T> void copyListElements(final List<T> list, Supplier<Collection<T>> targetCollection) {
        list.forEach(targetCollection.get()::add);
    }
    
    
    private void init() {
    	
		Item ite1 = new Item("10000", "Green Garden Gnomes", "FLW-003-12", "Green Garden Gnomes", "11-01-2019", 5.00,
				5, 3, 7, 1,1,110001);
		Item ite2 = new Item("10001", "Yellow Garden Gnomes", "FLW-003-13", "Green Garden Gnomes", "11-01-2019", 5.00,
				5, 3, 7, 1, 3, 110001);
		Item ite3 = new Item("10002", "Red Garden Gnomes", "FLW-003-14", "Green Garden Gnomes", "11-01-2019", 5.00,
				5, 3, 7, 1, 1, 110001);
		Item ite4 = new Item("10003", "Red Tall Garden Gnomes", "FLW-003-15", "Green Garden Gnomes", "11-01-2019", 5.00,
				5, 3, 17, 1, 4, 110001);
		Item ite5 = new Item("10004", "Green Tall Garden Gnomes", "FLW-003-16", "Green Garden Gnomes", "11-01-2019", 5.00,
				5, 3, 17, 1, 2, 110001);
		
		mongoTemplate.save(ite1);
		mongoTemplate.save(ite2);
		mongoTemplate.save(ite3);
		mongoTemplate.save(ite4);
		mongoTemplate.save(ite5);
		
		ite1 = new Item("100100", "Dozen Rose Stems", "FLW-024-10", "Beatuiful collection of Assorterd Red Roses", "12-01-2019", 15.00,
				5, 3, 27, 1,2, 110023);
		ite2 = new Item("100101", "Dozen Rose Stems", "FLW-024-11", "Beatuiful collection of Assorterd Red Roses w/ Vase", "11-01-2019", 15.00,
				5, 3, 27, 1, 1, 110023);
		ite3 = new Item("100102", "Dozen Rose Stems", "FLW-024-12", "Beatuiful collection of Assorterd Red Roses w/ Vase and chocolate", "11-01-2019", 15.00,
				5, 3, 27, 1,3, 110023);
		
		mongoTemplate.save(ite1);
		mongoTemplate.save(ite2);
		mongoTemplate.save(ite3);
		
		List<Item> items = new ArrayList<Item>();
		items.add(ite1);items.add(ite2);items.add(ite3);items.add(ite4);items.add(ite5);
		Product prd1 = new Product("110001", "Garden Gnomes", "Garden Gnomes", "European collection of Garden Gnomes", items);
		Shipment shipment = new Shipment("10110", "", Calendar.getInstance().getTime(), Calendar.getInstance().getTime(),null);
		Order ord1 = new Order("10023", items, shipment, 15.00F, 14.00F);

		items = new ArrayList<Item>();
		items.add(ite1);items.add(ite2);items.add(ite3);
		Product prd2 = new Product("110023", "Roses", "Dozen Rose Stems", "Beatuiful collection of Assorterd Red Roses", items);
		shipment = new Shipment("101010", "", Calendar.getInstance().getTime(), Calendar.getInstance().getTime(),null);
		Order ord2 = new Order("10023", items, shipment, 15.00F, 14.00F);
		
		items = new ArrayList<Item>();
		items.add(ite1);items.add(ite3);items.add(ite5);
		Address customeraddress = new Address("100", "100","Miller Street", "New Haven", "06721", "USA");
		Address deliveryaddress = new Address("101", "21A","Tilden Ave", "Los Angelos", "99017", "USA");
		Card card = new Card("4227-2145-1624-1927", "07/2020","982");
		Customer customer = new Customer("10000", "Sanjay", "Gupta", "ssanjay",  Arrays.asList(customeraddress), Arrays.asList(card));
        Order ord3 = new Order("10023", "100000",customer, deliveryaddress, card, items, shipment, 15.00F, 14.00F);
		
		mongoTemplate.save(ite1);
		mongoTemplate.save(ite2);
		mongoTemplate.save(ite3);
		mongoTemplate.save(ite4);
		mongoTemplate.save(ite5);

		mongoTemplate.save(prd1);
		mongoTemplate.save(prd2);	
		
		mongoTemplate.save(customeraddress);
		mongoTemplate.save(deliveryaddress);
		mongoTemplate.save(card);
		mongoTemplate.save(customer);
		
		mongoTemplate.save(ord1);
		mongoTemplate.save(ord2);
		mongoTemplate.save(ord3);
    }

	@Override
	public Order findOrderById(String orderid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order updateOrder(String orderid, Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOrder(String orderid) {
		// TODO Auto-generated method stub
		
	}
}
	

