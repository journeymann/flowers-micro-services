package com.flowers.microservice.beans;

/**
 * address class entity type definition.
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 *
 */
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.flowers.microservice.constants.Country;
import com.flowers.microservice.constants.State;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "address")
@RestResource(exported = false)
public class Address extends Model{
	
	@Id
	private String entityId;
	private String status;
	private String type;
	private String description;

    private String number;
	@NotNull
	@Length(min = 1, max = 30)
	private String street;
	@NotNull
	@Length(min = 1, max = 50)
	private String city;
	private String state = State.DEFAULT_STATE;
	private String postCode;
	private String zip;
	private String country = Country.DEFAULT_COUNTRY;

	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	private LocalDateTime effectiveDate;

	protected Address(){
		super();
	}
	
    public Address(String id, String number, String street, String city, String postcode, String country) {
        this.entityId = id;
    	this.number = number;
        this.street = street;
        this.city = city;
        this.postCode = postcode;
        this.country = country;
        this.type="H";
    }
    
    public Address(String id, String number, String street, String city, String postcode, String country, String type) {
        this.entityId = id;
    	this.number = number;
        this.street = street;
        this.city = city;
        this.postCode = postcode;
        this.country = country;
        this.type=type;
    }	

	public Address populate(String pentityId, String pstatus, String ptype, String pdesc, String pnumber, String pstreet,  String pcity,  String pstate,
			String pzip, String postCode, String pcountry, LocalDateTime peffective) {
		this.entityId = pentityId;

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
	
	public Address (String pentityId,String pstatus, String ptype, String pdesc, String pnumber,  String pstreet,  String pcity,  String pstate,
			String pzip, String postCode, String pcountry, LocalDateTime peffective){
		populate(pentityId, pstatus, ptype, pdesc, pnumber, pstreet,  pcity,  pstate, pzip, postCode, pcountry, peffective);
	}

	/**
	 * @return the entityId
	 */
	public String getEntityId() {
		return entityId;
	}

	/**
	 * @param entityId the entityId to set
	 */
	@JsonSetter("id")
	public void setEntityId(String entityId) {
		this.entityId = entityId;
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

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the createdDate
	 */
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the modifiedDate
	 */
	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
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

	@Override
	public String toString(){
	
		return String.format("entityId: %s, status: %s, type: %s, "
				+ "description: %s, street: %s, city: %s, state: %s, zip: %s "
				+ "country : %s, createdDate : %s, modifiedDate : %s, effectiveDate : %s \n", 
				entityId, status, type, description, street, city, state, zip, country, createdDate,
				modifiedDate, effectiveDate);
	}
}