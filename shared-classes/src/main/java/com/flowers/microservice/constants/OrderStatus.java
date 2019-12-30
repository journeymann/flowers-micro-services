/**
 * 
 */
package com.flowers.microservice.constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  12/18/2018
 * @version 1.0
 * 
 *  <p>
 *  
 *  This Java class interface defines constants for available order statuses.
 *  <p>
 */
public @interface OrderStatus {
	
	  String PENDING = "PENDING";	  
	  String PROCESSED = "PROCESSED";
	  String FAILED = "FAILED";

	  public static final Set<String> validMethodTypes = new HashSet<String>(Arrays.asList(OrderStatus.PENDING,OrderStatus.PROCESSED,OrderStatus.FAILED));
	  public static final String DEFAULT_STATUS = OrderStatus.PENDING;
	  
	  String value();
}