/**
 * 
 */
package com.flowers.microservice.beans;

import java.time.LocalDateTime;
import javax.persistence.GeneratedValue;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  12/18/2017
 * @version 1.0
 *
 * Model abstract [super] class defines toString() method for output of JSON defined contents 
 * Very useful for defining common behaviors for all Model objects
 */
public abstract class Model{

	@GeneratedValue
	protected String entityId;
	protected LocalDateTime createdDate;
	protected LocalDateTime modifiedDate;
	
	public Model(){
		this.createdDate = LocalDateTime.now();
		this.modifiedDate = LocalDateTime.now();
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
	public void setEntityId(String entityId) {
		this.entityId = entityId;
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
	
}