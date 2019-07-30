/**
 * 
 */
package com.flowers.microservice.tax.constants;

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
 *  This Java class interface defines constants for available statuses.
 *  <p>
 */
public @interface Status {
	
	  String ACTIVE = "ACTIVE";
	  String INACTIVE = "INACTIVE";
	  String PENDING = "PENDING";	  

	  public static final Set<String> validMethodTypes = new HashSet<String>(Arrays.asList(Status.ACTIVE,Status.INACTIVE,Status.PENDING));
	  public static final String DEFAULT_STATUS = Status.ACTIVE;
	  
	  String value();
}