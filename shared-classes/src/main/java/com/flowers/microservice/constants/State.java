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
public @interface State {
	
	  String AL = "Alabama";
	  String AK = "Alaska";
	  String AZ = "Arizona";
	  String AR = "Arkansas";
	  String CA = "California";
	  String CO = "Colorado";
	  String CT = "Connecticut";
	  String DE = "Delaware";
	  String DC = "District Of Columbia";
	  String FL = "Florida";
	  String GA = "Georgia";
	  String HI = "Hawaii";
	  String ID = "Idaho";
	  String IL = "Illinois";
	  String IN = "Indiana";
	  String IA = "Iowa";
	  String KS = "Kansas";
	  String KY = "Kentucky";
	  String LA = "Louisiana";
	  String ME = "Maine";
	  String MD = "Maryland";
	  String MA = "Massachusetts";
	  String MI = "Michigan";
	  String MN = "Minnesota";
	  String MS = "Mississippi";
	  String MO = "Missouri";
	  String MT = "Montana";
	  String NE = "Nebraska";
	  String NV = "Nevada";
	  String NH = "New Hampshire";
	  String NJ = "New Jersey";
	  String NM = "New Mexico";
	  String NY = "New York";
	  String NC = "North Carolina";
	  String ND = "North Dakota";
	  String OH = "Ohio";
	  String OK = "Oklahoma";
	  String OR= "Oregon";
	  String PA = "Pennsylvania";
	  String RI = "Rhode Island";
	  String SC = "South Carolina";
	  String SD = "South Dakota";
	  String TN = "Tennessee";
	  String TX = "Texas";
	  String UT = "Utah";
	  String VT = "Vermont";
	  String VA = "Virginiav";
	  String WA = "Washington";
	  String WV = "West Virginia";
	  String WI = "Wisconsinv";
	  String WY = "Wyoming";

	  public static final Set<String> validStates = new HashSet<String>(
			  Arrays.asList(State.NY,State.NJ, State.CT,State.RI,State.MA, State.PA,State.VT,State.MN));
	   
	  public static final String DEFAULT_STATE = State.NY;
	  
	  String value();
}