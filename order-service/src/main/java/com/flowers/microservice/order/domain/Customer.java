package com.flowers.microservice.order.domain;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;

import com.flowers.microservice.order.constants.Status;
import com.flowers.microservice.order.domain.billing.Card;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  01/15/2018
 * @version 1.0
 * 
 * customer class entity type definition.
 */
@Document(collection = "customers")
@RestResource(exported = false)
public class Customer extends Model{
	
	protected String customerId;
	protected String firstname;
	protected String lastname;
	protected String status;
	protected List<Address> address;
	protected List<EmailAddress> email;
	protected List<Phone> phone;
	protected List<Card> card;

	
	public Customer(){
		
	}
	
	public Customer(String customerId, String firstname, String lastname, List<Address> address, List<EmailAddress> email, List<Phone> phone, List<Card> card){
		
		this.customerId=customerId;
		this.firstname=firstname;
		this.lastname=lastname;
		this.address=address;		
		this.email=email;
		this.phone=phone;
		this.card=card;
		this.status=Status.DEFAULT_STATUS;
	}		
	
	public Customer(String customerId, String firstname, String lastname, String status, List<Address> address, List<EmailAddress> email, List<Phone> phone){
		
		this.customerId=customerId;
		this.firstname=firstname;
		this.lastname=lastname;
		this.address=address;		
		this.email=email;
		this.phone=phone;
		this.status=status;
	}	

	public Customer(String customerId, String firstname, String lastname){
		
		this.customerId=customerId;
		this.firstname=firstname;
		this.lastname=lastname;
		this.status=Status.DEFAULT_STATUS;
	}	
	
	
	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}
	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * @return the address
	 */
	public List<Address> getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(List<Address> address) {
		this.address = address;
	}

	/**
	 * @return the email
	 */
	public List<EmailAddress> getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(List<EmailAddress> email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public List<Phone> getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(List<Phone> phone) {
		this.phone = phone;
	}
	
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString(){
	
		return String.format("customerId: %s, firstname: %s, lastname: %s, "
				+ "address: %s, email: %s, phone: %s \n", 
				customerId, firstname, lastname, address, email, phone);
	}	
	
}
