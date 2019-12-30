package com.flowers.microservice.beans.contact;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.flowers.microservice.beans.Model;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  01/15/2018
 * @version 1.0
 * 
 * contact class entity type definition.
 */
public abstract class Contact extends Model{
	
	protected String status;
	protected String type;
	@NotNull @Length(min = 1, max = 120) protected String description;

	protected Contact(){
		super();
	}
		
	@Override
	public String toString(){
	
		return String.format("entityId: %s, status: %s, type: %s, "
				+ "description: %s, createdDate: %s, modifiedDate: %s, \n", 
				entityId, status, type, description,createdDate,modifiedDate);
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
	
	
}