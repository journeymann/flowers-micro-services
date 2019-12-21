package com.flowers.microservice.beans.billing;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;

import com.flowers.microservice.beans.Address;
import com.flowers.microservice.beans.Customer;
import com.flowers.microservice.beans.EmailAddress;
import com.flowers.microservice.beans.Phone;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  01/15/2018
 * @version 1.0
 * 
 * billing class entity type definition.
 */
@Document(collection = "billing")
@RestResource(exported = false)
public class Billing extends Customer{
	
	private CreditCard card;
	
	public Billing(){
		
	}
	
	public Billing(String billingid, String firstname, String lastname, List<Address> address, List<EmailAddress> email, List<Phone> phone, CreditCard card){
		
		super.customerId=billingid;
		super.firstname=firstname;
		super.lastname=lastname;
		super.addresses=address;
		super.email=email;
		super.phone=phone;
		this.card=card;
	}

	/**
	 * @return the card
	 */
	public CreditCard getCard() {
		return card;
	}

	/**
	 * @param card the card to set
	 */
	public void setCard(CreditCard card) {
		this.card = card;
	}	

}
