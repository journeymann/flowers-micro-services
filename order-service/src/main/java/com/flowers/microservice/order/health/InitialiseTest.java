package com.flowers.microservice.order.health;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;
import com.flowers.microservice.beans.Address;
import com.flowers.microservice.beans.Customer;
import com.flowers.microservice.beans.Item;
import com.flowers.microservice.beans.Order;
import com.flowers.microservice.beans.OrderItem;
import com.flowers.microservice.beans.Shipment;
import com.flowers.microservice.beans.billing.Billing;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
public class InitialiseTest {

	
	   public static void init(MongoTemplate mongoTemplate) { 
	    	
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
						
			List<Item> items = new ArrayList<Item>();
			items.add(ite1);items.add(ite2);items.add(ite3);items.add(ite4);items.add(ite5);
			
			Shipment shipment = new Shipment();
			
			OrderItem orderitem = new OrderItem("10001", items, shipment, 58.47F, 15.10F);
			
			List<OrderItem> orderitems = new ArrayList<OrderItem>();
			orderitems.add(orderitem);
			
			Address address = new Address("100", "25", "Elms Street", "Buxton", "PCS 231", "Canada");
			Customer customer = new Customer("1000", "Deryck", "Lamb");
			Billing billing = new Billing("10001", "Joseph", "Camel", null, null, null, null);
						
			mongoTemplate.save(new Order("1002",orderitems, LocalDate.now(),LocalDate.now(), 78.12, 5.477, LocalDate.now(), 2.55, 100L));
			mongoTemplate.save(new Order("1002",orderitems, LocalDate.now(),LocalDate.now(), 78.12, 5.477, LocalDate.now(), 2.55, 100L));
			
			mongoTemplate.save(new Order( "1000", orderitems, LocalDate.now(),LocalDate.now(), 78.25, 8.0, LocalDate.now(), address, customer, billing,  3.12, 25L));
    }
}
