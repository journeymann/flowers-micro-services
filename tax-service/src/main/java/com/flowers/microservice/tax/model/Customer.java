package com.flowers.microservice.tax.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;

import com.flowers.microservice.tax.constants.Status;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  01/15/2018
 * @version 1.0
 * 
 * customer class entity type definition.
 */
@Document(collection = "customers")
@RestResource(exported = false)
public class Customer {
	
	protected String customerId;
	protected String firstname;
	protected String lastname;
	protected String status;
	protected Address address;
	protected EmailAddress email;
	protected Phone phone;
	
	public Customer(){
		
	}
	
	public Customer(String customerId, String firstname, String lastname, Address address, EmailAddress email, Phone phone){
		
		this.customerId=customerId;
		this.firstname=firstname;
		this.lastname=lastname;
		this.address=address;		
		this.email=email;
		this.phone=phone;
		this.status=Status.DEFAULT_STATUS;
	}		
	
	public Customer(String customerId, String firstname, String lastname, String status, Address address, EmailAddress email, Phone phone){
		
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
	public Address getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}

	/**
	 * @return the email
	 */
	public EmailAddress getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(EmailAddress email) {
		this.email = email;
	}

	/**
	 * @return the phone
	 */
	public Phone getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(Phone phone) {
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
