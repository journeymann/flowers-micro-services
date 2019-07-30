/**
 * 
 */
package com.flowers.microservice.product.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import com.flowers.microservice.product.domain.Item;
import com.flowers.microservice.product.domain.Product;
import com.flowers.microservice.product.service.ProductService;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */

@Component
public class ProductServiceImpl implements ProductService {

    private final MongoTemplate mongoTemplate;
    
    @Autowired
    public ProductServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
        this.init();
    }
    
	public Product createProduct(Product product) {
		return mongoTemplate.save(product);
	};
	
	public Product findOneById(String id) {
	   Query query = new Query();
	   query.addCriteria(Criteria.where("name").is(id));
	   return mongoTemplate.findOne(query, Product.class);
	}
	
	public List<Product> findById(String id) {
	   Query query = new Query();
	   query.addCriteria(Criteria.where("name").is(id));
	   return mongoTemplate.find(query, Product.class);
	}

	public List<Product> findAllProductList(){
		
		return mongoTemplate.findAll(Product.class);
	}
	
	public List<Product> findAllProductListPaginated(int pageNumber, int pageSize) {
	   Query query = new Query();
	   query.skip(pageNumber * pageSize);
	   query.limit(pageSize);
	   return mongoTemplate.find(query, Product.class);
	}
	
	public Product updateOne(Product product) {
	   mongoTemplate.save(product);
	   return product;
	}
	
	public void deleteProduct(Product product){
		
		mongoTemplate.remove(product);
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

		items = new ArrayList<Item>();
		items.add(ite1);items.add(ite2);items.add(ite3);
		Product prd2 = new Product("110023", "Roses", "Dozen Rose Stems", "Beatuiful collection of Assorterd Red Roses", items);
		
		items = new ArrayList<Item>();
		items.add(ite1);items.add(ite3);items.add(ite5);

		mongoTemplate.save(ite1);
		mongoTemplate.save(ite2);
		mongoTemplate.save(ite3);
		mongoTemplate.save(ite4);
		mongoTemplate.save(ite5);

		mongoTemplate.save(prd1);
		mongoTemplate.save(prd2);	
    }

	@Override
	public Product findProductById(String productid) {
		return findOneById(productid);
	}

	@Override
	public Product updateProduct(String productid, Product product) {
		   if(findOneById(productid) != null) {
			   product.setProductId(productid);
			   mongoTemplate.save(product);
		   }
		   return product;
	}

	@Override
	public void deleteProduct(String productid) {
		mongoTemplate.remove(findOneById(productid));
	}

}