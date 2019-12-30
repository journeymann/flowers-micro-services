package com.flowers.microservice.beans.contact;

import java.util.List;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;
import com.flowers.microservice.beans.billing.CreditCard;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  01/15/2018
 * @version 1.0
 * 
 * customer class entity type definition.
 */
@Document(collection = "customers")
@RestResource(exported = false)
public class Customer extends AbstractCustomer{
	
	public Customer(){}
	
	public Customer(String customerId, String firstname, String lastname, List<Address> address, List<EmailAddress> email, List<Phone> phone, List<CreditCard> cards){
		super(customerId,  firstname,  lastname,  address,  email,  phone,  cards);
	}		
	
	public Customer(String customerId, String firstname, String lastname, String status, List<Address> address, List<EmailAddress> email, List<Phone> phone, List<CreditCard> cards){
		super( customerId,  firstname,  lastname,  status,  address,  email, phone, cards);
	}	

	public Customer(String customerId, String firstname, String lastname){
		super( customerId,  firstname,  lastname);
	}	
	
}
