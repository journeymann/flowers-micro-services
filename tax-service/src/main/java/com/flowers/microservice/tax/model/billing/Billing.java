package com.flowers.microservice.tax.model.billing;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;

import com.flowers.microservice.tax.model.Address;
import com.flowers.microservice.tax.model.Customer;
import com.flowers.microservice.tax.model.EmailAddress;
import com.flowers.microservice.tax.model.Phone;

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
	
	public Billing(String billingid, String firstname, String lastname, Address address, EmailAddress email, Phone phone, CreditCard card){
		
		super.customerId=billingid;
		super.firstname=firstname;
		super.lastname=lastname;
		super.address=address;
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
