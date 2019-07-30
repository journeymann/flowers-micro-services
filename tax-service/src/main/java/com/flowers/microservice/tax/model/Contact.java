package com.flowers.microservice.tax.model;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.flowers.microservice.tax.constants.Status;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  01/15/2018
 * @version 1.0
 * 
 * contact class entity type definition.
 */
public abstract class Contact extends Model{
	
	@Id
	protected String entityId;
	protected String status;
	protected String type;
	protected String contact;
	protected String description;
	protected LocalDateTime createdDate;
	protected LocalDateTime modifiedDate;
	protected LocalDateTime effectiveDate;
	
	protected Contact(){
		super();
	}
	
	public Contact (String pentityId, String ptype, String pcontact) {
		this.entityId = pentityId;
		
		this.status = Status.DEFAULT_STATUS;		
		this.type = ptype;
		this.contact = pcontact;
		this.description = "";
		this.createdDate = LocalDateTime.now();
		this.modifiedDate = LocalDateTime.now();
		this.effectiveDate = LocalDateTime.now();

	}

	public Contact (String pentityId, String pstatus, String ptype, String pcontact, String pdesc, LocalDateTime effective) {
		this.entityId = pentityId;
		
		this.status = pstatus;		
		this.type = ptype;
		this.contact = pcontact;
		this.description = pdesc;
		this.createdDate = LocalDateTime.now();
		this.modifiedDate = LocalDateTime.now();
		this.effectiveDate = effective;

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
	 * @return the contact
	 */
	public String getEmail() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setEmail(String contact) {
		this.contact = contact;
	}
	
	@Override
	public String toString(){
	
		return String.format("entityId: %s, status: %s, type: %s, "
				+ "contact: %s, description: %s, createdDate: %s, modifiedDate: %s, effectiveDate: %s \n", 
				entityId, status, type, contact, description,createdDate,modifiedDate,effectiveDate);
	}	
	
	
}