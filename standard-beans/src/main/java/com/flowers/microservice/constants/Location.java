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
 *  This Java class interface defines constants for available warehouse locations.
 *  <p>
 */
public @interface Location {
	
	  String NH_WH = "New Hampshire";
	  String NC_RALEIGH = "INACTIVE";
	  String NC_DURHAM = "PENDING";	 
	  String FL_WH = "Florida";	 

	  public static final Set<String> validMethodTypes = new HashSet<String>(Arrays.asList(Location.NH_WH,Location.NC_RALEIGH,Location.NC_DURHAM,Location.FL_WH));
	  public static final String DEFAULT_LOCATION = Location.NH_WH;
	  
	  String value();
}