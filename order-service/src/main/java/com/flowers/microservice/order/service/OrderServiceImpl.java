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

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.flowers.microservice.beans.OrderItem;
import com.flowers.microservice.beans.billing.CreditCard;
import com.flowers.microservice.beans.contact.Address;
import com.flowers.microservice.beans.contact.Customer;
import com.flowers.microservice.beans.contact.EmailAddress;
import com.flowers.microservice.beans.contact.Phone;
import com.flowers.microservice.common.LoggingHelper;
import com.flowers.microservice.beans.Item;
import com.flowers.microservice.beans.Product;
import com.flowers.microservice.beans.Shipment;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */

@Component
public class OrderServiceImpl implements OrderService {
	
	public static Logger LOGGER = LoggingHelper.getLogger(OrderServiceImpl.class); 
    private final MongoTemplate mongoTemplate;
    
    @Autowired
    public OrderServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.init();
    }
    
	public OrderItem createOrder(OrderItem orderItem) {
		return mongoTemplate.save(orderItem);
	};
	
	@Override
	public OrderItem findOneById(String id) {
	   Query query = new Query();
	   query.addCriteria(Criteria.where("name").is(id));
	   return mongoTemplate.findOne(query, OrderItem.class);
	}
	
	@Override
	public List<OrderItem> findById(String id) {
	   Query query = new Query();
	   query.addCriteria(Criteria.where("name").is(id));
	   return mongoTemplate.find(query, OrderItem.class);
	}

	public List<OrderItem> findAllOrderList(){
		
		return mongoTemplate.findAll(OrderItem.class);
	}
	
	@Override
	public List<OrderItem> findAllOrderListPaginated(int pageNumber, int pageSize) {
	   Query query = new Query();
	   query.skip(pageNumber * pageSize);
	   query.limit(pageSize);
	   return mongoTemplate.find(query, OrderItem.class);
	}
	
	@Override
	public OrderItem updateOne(OrderItem orderItem) {
	   mongoTemplate.save(orderItem);
	   return orderItem;
	}
	
	public void deleteOrder(OrderItem orderItem){
		
		mongoTemplate.remove(orderItem);
	};
	
    public static <T> void copyListElements(final List<T> list, Supplier<Collection<T>> targetCollection) {
        list.forEach(targetCollection.get()::add);
    }
    
	@Autowired
	private WebClient.Builder webClientBuilder;
	
	
	public Product findProductById(String id) {
		
		 LOGGER.info("findProductById: id={}", id);
		 
		 Product product = webClientBuilder
				 .baseUrl("http://localhost:8080")
				 .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE) 
				 .build()
				 .get().uri("/product/read/{productid}", id).retrieve().bodyToMono(Product.class)
				 .block();
		 		 
		 return product;
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
		OrderItem ord1 = new OrderItem("10023", items, shipment, 15.00F, 14.00F);

		items = new ArrayList<Item>();
		items.add(ite1);items.add(ite2);items.add(ite3);
		Product prd2 = new Product("110023", "Roses", "Dozen Rose Stems", "Beatuiful collection of Assorterd Red Roses", items);
		shipment = new Shipment("101010", "", Calendar.getInstance().getTime(), Calendar.getInstance().getTime(),null);
		OrderItem ord2 = new OrderItem("10023", items, shipment, 15.00F, 14.00F);
		
		items = new ArrayList<Item>();
		items.add(ite1);items.add(ite3);items.add(ite5);
		Address customeraddress = new Address("100", "100","Miller Street", "New Haven", "06721", "USA");
		Address deliveryaddress = new Address("101", "21A","Tilden Ave", "Los Angelos", "99017", "USA");
		Phone hphone = new Phone("247-859-7845", "H");
		Phone wphone = new Phone("723-239-2197", "W");
		EmailAddress email = new EmailAddress("test@mail.com","H");

		CreditCard card = new CreditCard("4227-2145-1624-1927", "07/2020","982");
		Customer customer = new Customer("10000", "Sanjay", "Gupta", Arrays.asList(customeraddress),  Arrays.asList(email),  Arrays.asList(hphone,wphone), Arrays.asList(card));
        OrderItem ord3 = new OrderItem("10023", "100000",customer, deliveryaddress, card, items, shipment, 15.00F, 14.00F);
		
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
	public OrderItem findOrderById(String orderid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderItem updateOrder(String orderid, OrderItem orderItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOrder(String orderid) {
		// TODO Auto-generated method stub
		
	}
}
	

