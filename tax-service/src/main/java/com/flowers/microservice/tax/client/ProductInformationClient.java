package com.flowers.microservice.tax.client;

import com.flowers.microservice.tax.model.Product;

/**
 * Interface for the Information micro-service.
 */
public interface ProductInformationClient {

  Product getProduct(String id);

}
