/**
 * 
 */
package com.flowers.microservice.shipping.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;
import com.flowers.microservice.beans.OrderItem;
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
	private WebClient.Builder webClientBuilder;
    
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
	public ShippingRate findOrderShippingRate(String orderid) {
		
		   ResponseSpec order = webClientBuilder
				   .baseUrl("http://localhost:5005")				   
				   .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				   .build()
				   .get()
				   .uri("/order/read{id}", orderid)
				   .retrieve();
   
		   OrderItem oi = order.bodyToMono(OrderItem.class).block();
   
		   Query query = new Query();
		   query.addCriteria(Criteria.where("state").is(oi.getAddress().getState()));
		   return mongoTemplate.findOne(query, ShippingRate.class);
	}

	@Override
	public TaxRate findOneTaxRate(String state) {
		   Query query = new Query();
		   query.addCriteria(Criteria.where("state").is(state));
		   return (TaxRate)mongoTemplate.findOne(query, TaxRate.class);
	};
	
	@Override
	public List<ShippingRate> findShippingRates(){
		return mongoTemplate.findAll(ShippingRate.class);
	};
}
	
