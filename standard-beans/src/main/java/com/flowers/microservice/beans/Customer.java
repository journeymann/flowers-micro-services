package com.flowers.microservice.beans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;

import com.flowers.microservice.constants.Status;
import com.flowers.microservice.beans.Address;
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
public class Customer extends Contact{
	
	@Id @GeneratedValue protected String customerId;
	protected String firstname;
	protected String lastname;
    @DBRef(lazy = true) protected List<Address> addresses = new ArrayList<Address>();
    @DBRef(lazy = true) protected List<CreditCard> cards = new ArrayList<CreditCard>();
    @DBRef(lazy = true) protected List<EmailAddress> email;
    @DBRef(lazy = true) protected List<Phone> phone;
	@Valid	private LocalDate startDate = LocalDate.now();
	@Valid	private LocalDate endDate;
	
	public Customer(){}
	
	public Customer(String customerId, String firstname, String lastname, List<Address> address, List<EmailAddress> email, List<Phone> phone, List<CreditCard> cards){
		
		this.customerId=customerId;
		this.firstname=firstname;
		this.lastname=lastname;
		this.addresses=address;		
		this.email=email;
		this.phone=phone;
		this.status=Status.DEFAULT_STATUS;
		this.cards = cards;
	}		
	
	public Customer(String customerId, String firstname, String lastname, String status, List<Address> address, List<EmailAddress> email, List<Phone> phone, List<CreditCard> cards){
		
		this.customerId=customerId;
		this.firstname=firstname;
		this.lastname=lastname;
		this.addresses=address;		
		this.email=email;
		this.phone=phone;
		this.status=status;
		this.cards = cards;
	}	

	public Customer(String customerId, String firstname, String lastname){
		
		this.customerId=customerId;
		this.firstname=firstname;
		this.lastname=lastname;
		this.status=Status.DEFAULT_STATUS;
	}	
		
	@Override
	public String toString(){
	
		return String.format("customerId: %s, firstname: %s, lastname: %s, "
				+ "address: %s, cards: %s, email: %s, phone: %s, status: %s, startDate: %s, endDate: %s \n", 
				customerId, firstname, lastname, Arrays.toString(addresses.toArray()), Arrays.toString(cards.toArray()), Arrays.toString(email.toArray()), 
				Arrays.toString(phone.toArray()), status, startDate, endDate);
	}	
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer that = (Customer) o;

        return this.customerId.equals(that.customerId);
    }
    
    @Override
    public int hashCode() {
        return customerId.hashCode();
    }
    
	public int compareTo(Customer that) {
		return this.customerId.compareTo(that.customerId);
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
	 * @return the addresses
	 */
	public List<Address> getAddresses() {
		return addresses;
	}

	/**
	 * @param addresses the addresses to set
	 */
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	/**
	 * @return the cards
	 */
	public List<CreditCard> getCards() {
		return cards;
	}

	/**
	 * @param cards the cards to set
	 */
	public void setCards(List<CreditCard> cards) {
		this.cards = cards;
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
	 * @return the startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
		
	
}
