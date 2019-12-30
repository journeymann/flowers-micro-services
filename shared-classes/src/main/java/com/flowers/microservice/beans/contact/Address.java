package com.flowers.microservice.beans.contact;


import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.flowers.microservice.constants.Country;
import com.flowers.microservice.constants.State;

/**
 * address class entity type definition.
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 *
 */

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "address")
@RestResource(exported = false)
public class Address extends Contact{
	
	@Id @GeneratedValue String addressId;
    private String number;
	@NotNull @Length(min = 1, max = 30)	private String street;
	@NotNull @Length(min = 1, max = 50)	private String city;
	private String state = State.DEFAULT_STATE;
	private String postCode;
	private String zip;
	private String country = Country.DEFAULT_COUNTRY;
	private LocalDateTime effectiveDate;

	public Address(){
		super();
	}
	
    public Address(String addressId, String number, String street, String city, String postcode, String country) {
        this.addressId = addressId;
    	this.number = number;
        this.street = street;
        this.city = city;
        this.postCode = postcode;
        this.country = country;
        this.type="H";
    }
    
    public Address(String addressId, String number, String street, String city, String postcode, String country, String type) {
        this.addressId =addressId;
    	this.number = number;
        this.street = street;
        this.city = city;
        this.postCode = postcode;
        this.country = country;
        this.type=type;
    }	

	public Address populate(String addressId, String pstatus, String ptype, String pdesc, String pnumber, String pstreet,  String pcity,  String pstate,
			String pzip, String postCode, String pcountry, LocalDateTime peffective) {
		this.addressId = addressId;

		this.status = pstatus;		
		this.type = ptype;
		this.description = pdesc;

		this.number = pnumber;
		this.street = pstreet;
		this.city = pcity;
		this.state = pstate;
		this.zip = pzip;
		this.postCode=postCode;
		this.country = pcountry;

		this.createdDate = LocalDateTime.now();
		this.modifiedDate = LocalDateTime.now();
		this.effectiveDate = peffective;

		return this;
	}

	public Address (String pstatus, String ptype, String pdesc,  String pnumber, String pstreet,  String pcity,  String pstate,
			String pzip, String postCode, String pcountry, LocalDateTime peffective){
		populate(null, pstatus, ptype, pdesc, pnumber, pstreet,  pcity,  pstate, pzip, postCode, pcountry, peffective);
	}
	
	public Address (String addressId,String pstatus, String ptype, String pdesc, String pnumber,  String pstreet,  String pcity,  String pstate,
			String pzip, String postCode, String pcountry, LocalDateTime peffective){
		populate(addressId, pstatus, ptype, pdesc, pnumber, pstreet,  pcity,  pstate, pzip, postCode, pcountry, peffective);
	}

	@Override
	public String toString(){
	
		return String.format("addressId: %s, status: %s, type: %s, number: %s,"
				+ "description: %s, street: %s, city: %s, postcode: %s, state: %s, zip: %s "
				+ "country : %s, createdDate : %s, modifiedDate : %s, effectiveDate : %s \n", 
				addressId, status, type, number, description, street, city, state, zip, postCode, country, createdDate,
				modifiedDate, effectiveDate);
	}

	/**
	 * @return the addressId
	 */
	public String getAddressId() {
		return addressId;
	}

	/**
	 * @param addressId the addressId to set
	 */
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the postCode
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * @param postCode the postCode to set
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * @return the zip
	 */
	public String getZip() {
		return zip;
	}

	/**
	 * @param zip the zip to set
	 */
	public void setZip(String zip) {
		this.zip = zip;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @return the effectiveDate
	 */
	public LocalDateTime getEffectiveDate() {
		return effectiveDate;
	}

	/**
	 * @param effectiveDate the effectiveDate to set
	 */
	public void setEffectiveDate(LocalDateTime effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	
	
}