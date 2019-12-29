package com.flowers.microservice.beans.billing;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.flowers.microservice.beans.contact.AbstractCustomer;
import com.flowers.microservice.beans.contact.Address;
import com.flowers.microservice.beans.contact.EmailAddress;
import com.flowers.microservice.beans.contact.Phone;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  01/15/2018
 * @version 1.0
 * 
 * billing class entity type definition.
 */
@Entity
@JsonRootName(value = "billing")
@ApiModel(description="Billing Information.")
@Document(collection = "billing")
@RestResource(exported = false)
public class Billing extends AbstractCustomer{
	
	@ApiModelProperty(notes="Credit Card", required = false)
	private CreditCard card;
	@ApiModelProperty(notes="Billing Id", required = false)
	@Id @GeneratedValue private String billingId;
	@ApiModelProperty(notes="Billing Date", required = false)
	@Valid	private LocalDateTime billingDate;

	public Billing(){}
	
	public Billing(String billingid, String firstname, String lastname, List<Address> address, List<EmailAddress> email, List<Phone> phone, CreditCard card){
		
		this.customerId=billingid;
		this.firstname=firstname;
		this.lastname=lastname;
		this.addresses=address;
		this.email=email;
		this.phone=phone;
		this.card=card;
	}


	@Override
	public String toString(){
	
		return String.format("customerId: %s, firstname: %s, lastname: %s, "
				+ "address: %s, cards: %s, email: %s, phone: %s, status: %s, card: %s, billingDate: %s \n", 
				customerId, firstname, lastname, Arrays.toString(addresses.toArray()), Arrays.toString(cards.toArray()), Arrays.toString(email.toArray()), 
				Arrays.toString(phone.toArray()), status, card, billingDate);
	}	
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Billing that = (Billing) o;

        return this.billingId.equals(that.billingId);
    }
    
    @Override
    public int hashCode() {
        return billingId.hashCode();
    }
    
	public int compareTo(Billing that) {
		return this.billingId.compareTo(that.billingId);
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

	/**
	 * @return the billingId
	 */
	public String getBillingId() {
		return billingId;
	}

	/**
	 * @param billingId the billingId to set
	 */
	public void setBillingId(String billingId) {
		this.billingId = billingId;
	}

	/**
	 * @return the billingDate
	 */
	public LocalDateTime getBillingDate() {
		return billingDate;
	}

	/**
	 * @param billingDate the billingDate to set
	 */
	public void setBillingDate(LocalDateTime billingDate) {
		this.billingDate = billingDate;
	}
		
}
