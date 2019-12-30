/**
 * 
 */
package com.flowers.microservice.beans;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
public class State {

	  public static List<State> States;

	  static {
	    States = new ArrayList<State>(50);
	    States.add(new State("AL", "Alabama"));
	    States.add(new State("AK", "Alaska"));
	    States.add(new State("AZ", "Arizona"));
	    States.add(new State("AR", "Arkansas"));
	    States.add(new State("CA", "California"));
	    States.add(new State("CO", "Colorado"));
	    States.add(new State("CT", "Connecticut"));
	    States.add(new State("DE", "Delaware"));
	    States.add(new State("DC", "District Of Columbia"));
	    States.add(new State("FL", "Florida"));
	    States.add(new State("GA", "Georgia"));
	    States.add(new State("HI", "Hawaii"));
	    States.add(new State("ID", "Idaho"));
	    States.add(new State("IL", "Illinois"));
	    States.add(new State("IN", "Indiana"));
	    States.add(new State("IA", "Iowa"));
	    States.add(new State("KS", "Kansas"));
	    States.add(new State("KY", "Kentucky"));
	    States.add(new State("LA", "Louisiana"));
	    States.add(new State("ME", "Maine"));
	    States.add(new State("MD", "Maryland"));
	    States.add(new State("MA", "Massachusetts"));
	    States.add(new State("MI", "Michigan"));
	    States.add(new State("MN", "Minnesota"));
	    States.add(new State("MS", "Mississippi"));
	    States.add(new State("MO", "Missouri"));
	    States.add(new State("MT", "Montana"));
	    States.add(new State("NE", "Nebraska"));
	    States.add(new State("NV", "Nevada"));
	    States.add(new State("NH", "New Hampshire"));
	    States.add(new State("NJ", "New Jersey"));
	    States.add(new State("NM", "New Mexico"));
	    States.add(new State("NY", "New York"));
	    States.add(new State("NC", "North Carolina"));
	    States.add(new State("ND", "North Dakota"));
	    States.add(new State("OH", "Ohio"));
	    States.add(new State("OK", "Oklahoma"));
	    States.add(new State("OR", "Oregon"));
	    States.add(new State("PA", "Pennsylvania"));
	    States.add(new State("RI", "Rhode Island"));
	    States.add(new State("SC", "South Carolina"));
	    States.add(new State("SD", "South Dakota"));
	    States.add(new State("TN", "Tennessee"));
	    States.add(new State("TX", "Texas"));
	    States.add(new State("UT", "Utah"));
	    States.add(new State("VT", "Vermont"));
	    States.add(new State("VA", "Virginia"));
	    States.add(new State("WA", "Washington"));
	    States.add(new State("WV", "West Virginia"));
	    States.add(new State("WI", "Wisconsin"));
	    States.add(new State("WY", "Wyoming"));
	  }

	  public State() {
		 this.code = null;
		 this.description = null;
	  }

	  public State(String code, String description) {
	    this.code = code;
	    this.description = description;
	  }

	  private String code ;;

	  private String description ;

	  public String toString() {
	    return String.format("State: %s, desc: %s", code, description);
	  }

	public static List<State> getiStates() {
		return States;
	}

	public static void setiStates(List<State> States) {
		State.States = States;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	}
