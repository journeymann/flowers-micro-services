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
 *  This Java class interface defines constants for available countries.
 *  <p>
 */
public @interface Country {
	
	  String USA = "USA";
	  String CAN = "CAN";
	  String MEX = "MEX";

	  public static final Set<String> validMethodTypes = new HashSet<String>(Arrays.asList(Country.USA,Country.CAN, Country.MEX));
	  public static final String DEFAULT_COUNTRY = Country.USA;
	  
	  String value();
}