package com.flowers.microservice.tax.client;

import com.flowers.microservice.tax.model.Order;

/**
 * Interface for the Information micro-service.
 */
public interface OrderInformationClient {

  Order getOrder(String id);

}