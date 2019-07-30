package com.flowers.microservice.tax.client;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flowers.microservice.tax.model.Product;

import java.io.IOException;

/**
 * An adapter to communicate with information micro-service.
 */
@Component
public class ProductInformationClientImpl implements ProductInformationClient {

  private static final Logger LOGGER = LoggerFactory.getLogger(ProductInformationClientImpl.class);
  
  @Value("${flowers.product.service.url}")
  private String productServiceUrl;
  @Value("${flowers.product.service.secret}")
  private transient String productSecret;  
	
  @Override
  public Product getProduct(String id) {
	  
	Product response = null;
    try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
      HttpGet httpGet = new HttpGet(String.format("%s/read?id=%s", productServiceUrl, id));
      try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
        
		ObjectMapper mapper = new ObjectMapper();
		return (Product)mapper.readValue(EntityUtils.toString(httpResponse.getEntity()), Product.class);
        
      }
    } catch (IOException e) {
      LOGGER.error("Exception caught.", e);
    }
    return response;
  }
}