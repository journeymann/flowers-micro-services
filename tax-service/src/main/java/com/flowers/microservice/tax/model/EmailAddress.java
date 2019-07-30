package com.flowers.microservice.tax.model;

import java.time.LocalDateTime;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;

import com.flowers.microservice.tax.constants.Status;
/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  01/15/2018
 * @version 1.0
 * 
 * email address class entity type definition.
 */

@Document(collection = "email")
@RestResource(exported = false)
public final class EmailAddress extends Contact{
	
	protected EmailAddress(){
		super();
	}
	
	public EmailAddress (String pentityId, String ptype, String pemail) {
		this.entityId = pentityId;
		
		this.status = Status.DEFAULT_STATUS;		
		this.type = ptype;
		this.contact = pemail;
		this.description = "";
		this.createdDate = LocalDateTime.now();
		this.modifiedDate = LocalDateTime.now();
		this.effectiveDate = LocalDateTime.now();

	}

	public EmailAddress (String pentityId, String pstatus, String ptype, String pemail, String pdesc, LocalDateTime effective) {
		this.entityId = pentityId;
		this.status = pstatus;		
		this.type = ptype;
		this.contact = pemail;
		this.description = pdesc;
		this.createdDate = LocalDateTime.now();
		this.modifiedDate = LocalDateTime.now();
		this.effectiveDate = effective;

	}
}