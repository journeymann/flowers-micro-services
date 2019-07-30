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
import com.flowers.microservice.tax.model.Order;

import java.io.IOException;

/**
 * An adapter to communicate with information micro-service.
 */
@Component
public class OrderInformationClientImpl implements OrderInformationClient {

  private static final Logger LOGGER = LoggerFactory.getLogger(OrderInformationClientImpl.class);
  
  @Value("${flowers.order.service.url}")
  private String orderServiceUrl;
  @Value("${flowers.order.service.secret}")
  private transient String orderSecret;
  	
  @Override
  public Order getOrder(String id) {
	  
	Order response = null;
    try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
      HttpGet httpGet = new HttpGet(String.format("%s/read?id=%s", orderServiceUrl, id));
      try (CloseableHttpResponse httpResponse = httpClient.execute(httpGet)) {
        
		ObjectMapper mapper = new ObjectMapper();
		return (Order)mapper.readValue(EntityUtils.toString(httpResponse.getEntity()), Order.class);
        
      }
    } catch (IOException e) {
      LOGGER.error("Exception caught.", e);
    }
    return response;
  }
}