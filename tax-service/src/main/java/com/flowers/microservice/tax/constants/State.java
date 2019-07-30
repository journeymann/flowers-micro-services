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
 *  This Java class interface defines constants for available countries.
 *  <p>
 */
public @interface State {
	
	  String NY = "NY";
	  String NJ = "NJ";
	  String CT = "CT";
	  String RI = "RI";
	  String MA = "MA";
	  String PA = "PA";
	  String VT = "VT";
	  String MN = "MN";	  

	  public static final Set<String> validMethodTypes = new HashSet<String>(
			  Arrays.asList(State.NY,State.NJ, State.CT,State.RI,State.MA, State.PA,State.VT,State.MN));
	  
	  public static final String DEFAULT_STATE = State.NY;
	  
	  String value();
}