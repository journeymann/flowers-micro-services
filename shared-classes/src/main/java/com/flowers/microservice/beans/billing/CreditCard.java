package com.flowers.microservice.beans.billing;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.LuhnCheck;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.flowers.microservice.beans.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  01/15/2018
 * @version 1.0
 * 
 * credit card class entity type definition.
 */
@Entity
@JsonRootName(value = "creditcard")
@ApiModel(description="Credit Card Information.")
@Document(collection = "creditcard")
@RestResource(exported = false)
public class CreditCard extends Model implements java.io.Serializable {
	
	@JsonIgnore
	private static final long serialVersionUID = -850054659252440226L;
	@ApiModelProperty(notes="Credit Card Expire Date", required = true)
	@NotNull private String cardExpiryDate;
	@ApiModelProperty(notes="Credit Card Number", required = true)
	@NotNull @LuhnCheck private String cardNumber;
	@ApiModelProperty(notes="Credit Card Type", required = true)
	@NotNull private String cardType;

	public CreditCard() {
	}

	public CreditCard(
			String cardExpiryDate,
			String cardNumber,
			String cardType) {
		this.cardExpiryDate = cardExpiryDate;
		this.cardNumber = cardNumber;
		this.cardType = cardType;
	}


	private Object __equalsCalc = null;
	public synchronized boolean equals(Object obj) {
		if (!(obj instanceof CreditCard)) return false;
		CreditCard other = (CreditCard) obj;
		if (this == obj) return true;
		if (__equalsCalc != null) {
			return (__equalsCalc == obj);
		}
		__equalsCalc = obj;
		boolean _equals;
		_equals = true && 
				((this.cardExpiryDate==null && other.cardExpiryDate==null) || 
						(this.cardExpiryDate!=null &&
						this.cardExpiryDate.equals(other.cardExpiryDate))) &&
				((this.cardNumber==null && other.cardExpiryDate==null) || 
						(this.cardNumber!=null &&
						this.cardNumber.equals(other.cardExpiryDate))) &&
				((this.cardType==null && other.cardExpiryDate==null) || 
						(this.cardType!=null &&
						this.cardType.equals(other.cardExpiryDate)));
		__equalsCalc = null;
		return _equals;
	}

	private boolean __hashCodeCalc = false;
	public synchronized int hashCode() {
		if (__hashCodeCalc) {
			return 0;
		}
		__hashCodeCalc = true;
		int _hashCode = 1;
		if (cardExpiryDate != null) {
			_hashCode += cardExpiryDate.hashCode();
		}
		if (cardExpiryDate != null) {
			_hashCode += cardExpiryDate.hashCode();
		}
		if (cardExpiryDate != null) {
			_hashCode += cardExpiryDate.hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}
}