/**
 * 
 */
package com.flowers.microservice.inventory.service;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import com.flowers.microservice.beans.Inventory;
import com.flowers.microservice.inventory.service.InventoryService;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */

@Component
public class InventoryServiceImpl implements InventoryService {

    private final MongoTemplate mongoTemplate;
    
    @Autowired
    public InventoryServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
	
	public Inventory findById(String id) {
	   Query query = new Query();
	   query.addCriteria(Criteria.where("name").is(id));
	   return mongoTemplate.findOne(query, Inventory.class);
	}

    public static <T> void copyListElements(final List<T> list, Supplier<Collection<T>> targetCollection) {
        list.forEach(targetCollection.get()::add);
    }
 
	@Override
	public Inventory findProductCountById(String productid) {
		return findById(productid);
	}

	@Override
	public Inventory updateProductCount(String productid, Long count) {
		   if(findById(productid) != null) {
			   return mongoTemplate.save(new Inventory(productid, count));
		   }
		   
		   return new Inventory();
	}


}