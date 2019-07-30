package com.flowers.microservice.tax.model.billing;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  01/15/2018
 * @version 1.0
 * 
 * credit card class entity type definition.
 */

@Document(collection = "creditcard")
@RestResource(exported = false)
public class CreditCard  implements java.io.Serializable {

	private static final long serialVersionUID = -850054659252440226L;
	private String cardExpiryDate;
	private String cardNumber;
	private String cardType;

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

	/**
	 * Gets the cardExpiryDate value for this CreditCard.
	 * 
	 * @return cardExpiryDate
	 */
	public String getCardExpiryDate() {
		return cardExpiryDate;
	}


	/**
	 * Sets the cardExpiryDate value for this CreditCard.
	 * 
	 * @param cardExpiryDate
	 */
	public void setCardExpiryDate(String cardExpiryDate) {
		this.cardExpiryDate = cardExpiryDate;
	}


	/**
	 * Gets the cardNumber value for this CreditCard.
	 * 
	 * @return cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}


	/**
	 * Sets the cardNumber value for this CreditCard.
	 * 
	 * @param cardNumber
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}


	/**
	 * Gets the cardType value for this CreditCard.
	 * 
	 * @return cardType
	 */
	public String getCardType() {
		return cardType;
	}


	/**
	 * Sets the cardType value for this CreditCard.
	 * 
	 * @param cardType
	 */
	public void setCardType(String cardType) {
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
				((this.cardExpiryDate==null && other.getCardExpiryDate()==null) || 
						(this.cardExpiryDate!=null &&
						this.cardExpiryDate.equals(other.getCardExpiryDate()))) &&
				((this.cardNumber==null && other.getCardNumber()==null) || 
						(this.cardNumber!=null &&
						this.cardNumber.equals(other.getCardNumber()))) &&
				((this.cardType==null && other.getCardType()==null) || 
						(this.cardType!=null &&
						this.cardType.equals(other.getCardType())));
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
		if (getCardExpiryDate() != null) {
			_hashCode += getCardExpiryDate().hashCode();
		}
		if (getCardNumber() != null) {
			_hashCode += getCardNumber().hashCode();
		}
		if (getCardType() != null) {
			_hashCode += getCardType().hashCode();
		}
		__hashCodeCalc = false;
		return _hashCode;
	}
}