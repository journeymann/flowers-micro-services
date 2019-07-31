package com.flowers.microservice.order.domain;

import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "address")
public class Address extends Contact{

    private String number;
    private String street;
    private String city;
    private String postcode;
    private String country;
    
    public Address() {
    }

    public Address(String id, String number, String street, String city, String postcode, String country, String type) {
        this.entityId = id;
    	this.number = number;
        this.street = street;
        this.city = city;
        this.postcode = postcode;
        this.country = country;
        super.type="H";
    }
    
    public Address(String number, String street, String city, String postcode, String country, String type) {
        this.number = number;
        this.street = street;
        this.city = city;
        this.postcode = postcode;
        this.country = country;
        super.type=type;
    }
    
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address obj = (Address) o;

        return getEntityId().equals(obj.getEntityId());

    }
    
    @Override
    public int hashCode() {
        return getEntityId().hashCode();
    }
    @Override
    public String toString() {
        return 	String.format("Address{id: %s,number: %s,street: %s,city: %s,postcode: %s,country: %s}",entityId,number,street,city,postcode,country);

    }

}