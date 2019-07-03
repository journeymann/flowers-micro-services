/**
 * 
 */
package com.flowers.microservice.order.constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author cgordon
 * @created 12/18/2017
 * @version 1.0
 * 
 *  <p>
 *  
 *  This Java class interface defines constants for available Time zones.
 *  <p>
 */
public @interface TimeZones {
	  String UTC = "UTC";
	  String ISO8601 = "ISO 8601";

	  public static final Set<String> validMethodTypes = new HashSet<String>(Arrays.asList(TimeZones.UTC,TimeZones.ISO8601));
	  public static final String DEFAULT_TIMEZONE = TimeZones.UTC;
	  
	  String value();
}
