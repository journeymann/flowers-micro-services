package com.flowers.microservice.inventory.health;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.MongoTemplate;

import com.flowers.microservice.beans.Item;
import com.flowers.microservice.beans.Product;

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
			Product prd1 = new Product("110001", "Garden Gnomes", "Garden Gnomes", "European collection of Garden Gnomes", items);
			mongoTemplate.save(prd1);
			
			ite1 = new Item("100100", "Dozen Rose Stems", "FLW-024-10", "Beatuiful collection of Assorterd Red Roses", "12-01-2019", 15.00,
					5, 3, 27, 1,2, 110023);
			ite2 = new Item("100101", "Dozen Rose Stems", "FLW-024-11", "Beatuiful collection of Assorterd Red Roses w/ Vase", "11-01-2019", 15.00,
					5, 3, 27, 1, 1, 110023);
			ite3 = new Item("100102", "Dozen Rose Stems", "FLW-024-12", "Beatuiful collection of Assorterd Red Roses w/ Vase and chocolate", "11-01-2019", 15.00,
					5, 3, 27, 1,3, 110023);

			items = new ArrayList<Item>();
			items.add(ite1);items.add(ite2);items.add(ite3);
			Product prd2 = new Product("110023", "Roses", "Dozen Rose Stems", "Beatuiful collection of Assorterd Red Roses", items);
			
			items = new ArrayList<Item>();
			items.add(ite1);items.add(ite3);items.add(ite5);
			mongoTemplate.save(prd2);
	    }
}
