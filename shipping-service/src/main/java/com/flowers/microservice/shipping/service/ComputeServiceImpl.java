/**
 * 
 */
package com.flowers.microservice.shipping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.flowers.microservice.beans.ShippingRate;
import com.flowers.microservice.beans.TaxRate;


/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */

@Service
public class ComputeServiceImpl implements ComputeService {

    private final MongoTemplate mongoTemplate;
    
    @Autowired
    public ComputeServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

	@Override
	public ShippingRate findOneShippingRate(String state) {
		   Query query = new Query();
		   query.addCriteria(Criteria.where("state").is(state));
		   return mongoTemplate.findOne(query, ShippingRate.class);
	}

	@Override
	public TaxRate findOneTaxRate(String state) {
		   Query query = new Query();
		   query.addCriteria(Criteria.where("state").is(state));
		   return (TaxRate)mongoTemplate.findOne(query, TaxRate.class);
	};
}
	
