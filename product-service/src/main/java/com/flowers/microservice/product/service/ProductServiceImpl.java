/**
 * 
 */
package com.flowers.microservice.product.service;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import com.flowers.microservice.beans.Product;
import com.flowers.microservice.product.service.ProductService;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */

@Service
public class ProductServiceImpl implements ProductService {
	
    private final MongoTemplate mongoTemplate;
    
    @Autowired
    public ProductServiceImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    
    @Override
	public Product createProduct(Product product) {
		return mongoTemplate.save(product);
	};
	
    @Override
	public Product findOneById(String id) {
	   Query query = new Query();
	   query.addCriteria(Criteria.where("name").is(id));
	   return mongoTemplate.findOne(query, Product.class);
	}
	
    @Override
	public List<Product> findById(String id) {
	   Query query = new Query();
	   query.addCriteria(Criteria.where("name").is(id));
	   return mongoTemplate.find(query, Product.class);
	}

    @Override
	public List<Product> findAllProductList(){
		return mongoTemplate.findAll(Product.class);
	}
	
    @Override
	public List<Product> findAllProductListPaginated(int pageNumber, int pageSize) {
	   Query query = new Query();
	   query.skip(pageNumber * pageSize);
	   query.limit(pageSize);
	   return mongoTemplate.find(query, Product.class);
	}
	
    @Override
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