/**
 * 
 */
package com.flowers.microservice.constants;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 * 
 *  <p>
 *  
 *  This Java class interface defines constants for available shipping methods.
 *  <p>
 */
public @interface ShippingMethod {
	
	  String USPS = "USPS";
	  String DHL = "DHL";
	  String UPS = "UPS";
	  String FEDEX = "FEDEX";


	  public static final Set<String> validMethodTypes = new HashSet<String>(Arrays.asList(ShippingMethod.USPS,ShippingMethod.DHL,ShippingMethod.UPS,ShippingMethod.FEDEX));
	  public static final String DEFAULT = ShippingMethod.USPS;
	  
	  String value();
}