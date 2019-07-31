package com.flowers.microservice.order.domain.billing;

import javax.persistence.GeneratedValue;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;

import com.flowers.microservice.order.domain.Address;
import com.flowers.microservice.order.domain.EmailAddress;
import com.flowers.microservice.order.domain.Model;
import com.flowers.microservice.order.domain.Phone;
import com.flowers.microservice.order.domain.Shipment;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  01/15/2018
 * @version 1.0
 * 
 * billing class entity type definition.
 */
@Document(collection = "billing")
@RestResource(exported = false)
public class Billing extends Model{
	
	private Card card;
	private String customerId;
	private String firstname;
	private String lastname;
	private String status;
	private Address address;
	private EmailAddress email;
	private Phone phone;
	
	public Billing(){
		
	}
	
	public Billing(String billingid, String firstname, String lastname, Address address, EmailAddress email, Phone phone, Card card){
		
		this.customerId=billingid;
		this.firstname=firstname;
		this.lastname=lastname;
		this.address=address;
		this.email=email;
		this.phone=phone;
		this.card=card;
	}

	@Id
	@GeneratedValue
	private String billingId;
	
	/**
	 * @return the card
	 */
	public Card getCard() {
		return card;
	}

	/**
	 * @param card the card to set
	 */
	public void setCard(Card card) {
		this.card = card;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public EmailAddress getEmail() {
		return email;
	}

	public void setEmail(EmailAddress email) {
		this.email = email;
	}

	public Phone getPhone() {
		return phone;
	}

	public void setPhone(Phone phone) {
		this.phone = phone;
	}	
	
	/**
	 * @return the id
	 */
	public String getId() {
		return billingId;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.billingId = id;
	}
		
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shipment obj = (Shipment) o;

        return getId().equals(obj.getId());

    }
    
    @Override
    public int hashCode() {
        return getId().hashCode();
    }
	@Override
	public String toString(){
	
		return String.format("Billing {billingId: %s, customerId: %s, firstname: %s, lastname: %s, "
				+ "address: %s, email: %s, phone: %s }\n", 
				billingId, customerId, firstname, lastname, address, email, phone);
	}	
}
