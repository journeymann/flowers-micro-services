/**
 * 
 */
package com.flowers.microservice.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import com.flowers.microservice.product.client.StatisticsServiceClient;
import com.flowers.microservice.product.domain.Product;
import com.flowers.microservice.product.repository.ProductRepository;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private StatisticsServiceClient statisticsClient;

	@Autowired
	private ProductRepository repository;

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("deprecation")
	public Product findProductByName(String productName) {
		Assert.hasLength(productName);
		return repository.findByName(productName);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Product create(Product product) {

		repository.save(product);

		return product;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Product update(String id, Product product) {

		Product update = repository.findOne(id)!=null? repository.save(product) : repository.update(product);

		statisticsClient.updateStatistics(update.getName(), update);
		
		return update;
	}

	@Override
	public Product findProductById(String id) {
		return repository.findOne(id);
	}

	@Override
	public List<Product> findAllProducts() {
		return repository.findAllProducts();
	}
	
	@Override
	public void delete(String id) {
		repository.delete(id);
	}
}