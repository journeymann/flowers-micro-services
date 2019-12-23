/**
 * 
 */
package com.flowers.microservice.tax.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.TypeReferences;
import org.springframework.stereotype.Service;

import com.flowers.microservice.beans.Order;
import com.flowers.microservice.beans.Product;
import com.flowers.microservice.beans.TaxRate;
import com.flowers.microservice.tax.config.AppConfigProperties;
import com.flowers.microservice.tax.proxy.AsyncGetService;
import com.flowers.microservice.tax.request.TaxRequest;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  12/11/2017
 * @version 1.0
 *
 */

@Service
public class TaxRateServiceImpl implements TaxRateService {

    @Value(value = "${http.timeout}")
    private long timeout;
    
    @Autowired
    private AppConfigProperties config;
	
    private final MongoTemplate mongoTemplate;
    
    @Autowired
    public TaxRateServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
    @Autowired
    private AsyncGetService asyncGetService;
	
	private Predicate<TaxRate> presetRateVerify  = (d)-> d.getRate() >= 0.0;
	private Function2<List<TaxRate>, String, List<TaxRate>> filterCounty = (x,y) ->  x.stream().filter(s->s.getCounty().contains((CharSequence) y)).collect(Collectors.toList());
	

	public TaxRate createTaxRate(TaxRate taxRate){

		return mongoTemplate.save(taxRate);
	};

	public TaxRate findTaxRateByRequest(TaxRequest taxdata){

		//TODO remove
		boolean fail = (Math.random() * 1) > 0.99; //probability 1:100 failure
		if(fail){ 
			String.valueOf(1/0); //divide by zero exception to simulate error hysterix test
		}
	    Query query = new Query();
	    query.addCriteria(Criteria.where("id").is(taxdata.getItemId()));
		return (TaxRate) mongoTemplate.findOne(query, TaxRate.class);	
	}

	public TaxRate findTaxRateById(String taxdataid){

		//TODO remove
		boolean fail = (Math.random() * 1) > 0.99; //probability 1:100 failure
		if(fail){ 
			String.valueOf(1/0); //divide by zero exception to simulate error hysterix test
		}
	    Query query = new Query();
	    query.addCriteria(Criteria.where("id").is(taxdataid));
		return (TaxRate) mongoTemplate.findOne(query, TaxRate.class);	
	};

	public TaxRate findTaxRateByZip(String zipcode){
	
		return mongoTemplate.findAll(TaxRate.class).stream().filter(p -> p.getZipcode().contains(zipcode) && presetRateVerify.test(p)).
				findFirst().orElse(new TaxRate());
	}
	
	public List<TaxRate> findAllTaxRates(){

		//TODO remove
		boolean fail = (Math.random() * 1) > 0.99; //probability 1:100 failure
		if(fail){ 
			String.valueOf(1/0); //divide by zero exception to simulate error hysterix test
		}

		return (ArrayList<TaxRate>) mongoTemplate.findAll(TaxRate.class);
	};

	public List<TaxRate> findAllTaxRateNamesLike(String term){

		
		return filterCounty.apply(mongoTemplate.findAll(TaxRate.class), term);
	}

	public List<TaxRate> findAllTaxRateList(){

		return mongoTemplate.findAll(TaxRate.class);
	}

	public TaxRate updateTaxRate(String taxdataid, TaxRate taxRate){
	    Query query = new Query();
	    query.addCriteria(Criteria.where("id").is(taxdataid));

		return mongoTemplate.findOne(query, TaxRate.class) != null? mongoTemplate.save(taxRate) : taxRate;
	};

	public void deleteTaxRate(String taxdataid){
	    Query query = new Query();
	    query.addCriteria(Criteria.where("id").is(taxdataid));
		mongoTemplate.remove(query, TaxRate.class);
	};

	@Override
	public TaxRate findOneTaxRate(String state) {
		   Query query = new Query();
		   query.addCriteria(Criteria.where("state").is(state));
		   return (TaxRate)mongoTemplate.findOne(query, TaxRate.class);
	};
	
	public Double calculateOrderTax(String orderid) {
	    try {		
	        Future<Resource<Order>> orderFuture = asyncGetService.postResource(config.getOrderUri(),orderid, new TypeReferences
	                .ResourceType<Order>() {
	        });
		        
			TaxRate rate = this.findTaxRateByZip(orderFuture.get(timeout, TimeUnit.SECONDS).getContent().getAddress().getZip());

			return orderFuture.get(timeout, TimeUnit.SECONDS).getContent().getOrderTotal() * (presetRateVerify.test(rate)? rate.getRate() : 2.00);

	    } catch (TimeoutException e) {
	        throw new IllegalStateException("Unable to create order due to timeout from one of the services.", e);
	    } catch (InterruptedException | ExecutionException e) {
	        throw new IllegalStateException("Unable to create order due to unspecified IO error.", e);
	    }	

	}	

	public Double calculateOrderTax(Order order) {
		TaxRate rate = this.findTaxRateByZip(order.getAddress().getZip());

		return order.getOrderTotal() * (presetRateVerify.test(rate)? rate.getRate() : 2.00);
	}	
	
	public Double calculateItemTax(String itemid) {
	    
	    try {		
	        Future<Resource<Product>> productFuture = asyncGetService.postResource(config.getProductUri(),itemid, new TypeReferences
	                .ResourceType<Product>() {
	        });
	
			return productFuture.get(timeout, TimeUnit.SECONDS).getContent().getPrice() * (Math.random() * 4.5);
			
	    } catch (TimeoutException e) {
	        throw new IllegalStateException("Unable to create order due to timeout from one of the services.", e);
	    } catch (InterruptedException | ExecutionException e) {
	        throw new IllegalStateException("Unable to create order due to unspecified IO error.", e);
	    }
	}	
	
	public Double calculateItemTax(Product item, String zipcode) {
    
        Query query = new Query();
	    query.addCriteria(Criteria.where("zipcode").is(zipcode));
		TaxRate taxrate = mongoTemplate.findOne(query, TaxRate.class);

		return item.getPrice() * (presetRateVerify.test(taxrate)? taxrate.getRate() : 2.00);
	}	
	
	public Double calculateOrderShippingTax(String orderid) {
	    try {		
	        Future<Resource<Order>> orderFuture = asyncGetService.postResource(config.getOrderUri(),orderid, new TypeReferences
	                .ResourceType<Order>() {
	        });
	
			return orderFuture.get(timeout, TimeUnit.SECONDS).getContent().getOrderTotal() * (Math.random() * 1.0);
			
	    } catch (TimeoutException e) {
	        throw new IllegalStateException("Unable to create order due to timeout from one of the services.", e);
	    } catch (InterruptedException | ExecutionException e) {
	        throw new IllegalStateException("Unable to create order due to unspecified IO error.", e);
	    }
	}	

	public static String calculateOrderDeliveryDate(String orderid){

		return String.valueOf(LocalDateTime.now().plusDays(3));
	}

	@FunctionalInterface
	public interface Function2<One, Two, Three> {
	    public Three apply(One one, Two two);
	}	

}

