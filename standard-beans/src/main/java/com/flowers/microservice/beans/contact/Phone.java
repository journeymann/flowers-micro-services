package com.flowers.microservice.beans.contact;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  01/15/2018
 * @version 1.0
 * 
 * phone class entity type definition.
 */
@Document(collection = "phone")
@RestResource(exported = false)
public class Phone extends Contact {
	
	@NotNull @Length(min = 1, max = 20) private String phone;
	@Id @GeneratedValue String phoneId;
	private LocalDateTime effectiveDate;
	
	public Phone(){}

	public Phone(String pnumber, String ptype){

		this.status = "ACT";		
		this.type = ptype;
		this.phone = pnumber;
		this.description = "";
		this.createdDate = LocalDateTime.now();
		this.modifiedDate = LocalDateTime.now();
		this.effectiveDate = LocalDateTime.now();
	}
	
	public Phone(String phoneId, String pnumber, String ptype){
		this.phoneId = phoneId;
		this.status = "ACT";		
		this.type = ptype;
		this.phone = pnumber;
		this.description = "";
		this.createdDate = LocalDateTime.now();
		this.modifiedDate = LocalDateTime.now();
		this.effectiveDate = LocalDateTime.now();
	}
	
	public Phone (String phoneId, String pstatus, String ptype, String pnumber, String pdesc, LocalDateTime effective) {
		this.phoneId = phoneId;
		this.status = pstatus;		
		this.type = ptype;
		this.phone = pnumber;
		this.description = pdesc;
		this.createdDate = LocalDateTime.now();
		this.modifiedDate = LocalDateTime.now();
		this.effectiveDate = effective;	
	}
	
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Phone that = (Phone) o;

        return this.phone.equals(that.phone);
    }
    
    @Override
    public int hashCode() {
        return phone.hashCode();
    }

    @Override
    public String toString() {
        return 	String.format("Phone{ phoneId: %s, status: %s, type: %s, email: %s, desc: %s, effectivedate: %s}",phoneId, status, type, phone, description, effectiveDate);

    }

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * @return the phoneId
	 */
	public String getPhoneId() {
		return phoneId;
	}

	/**
	 * @param phoneId the phoneId to set
	 */
	public void setPhoneId(String phoneId) {
		this.phoneId = phoneId;
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