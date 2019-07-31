package com.flowers.microservice.order.domain;

import java.time.LocalDateTime;
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
	
	public Phone(){
		super();
	}

	public Phone(String pnumber, String ptype){
		this.status = "ACT";		
		this.type = ptype;
		this.contact = pnumber;
		this.description = "";
		this.createdDate = LocalDateTime.now();
		this.modifiedDate = LocalDateTime.now();
		this.effectiveDate = LocalDateTime.now();
	}
	
	public Phone(String pentityId, String pnumber, String ptype){
		this.entityId = pentityId;
		this.status = "ACT";		
		this.type = ptype;
		this.contact = pnumber;
		this.description = "";
		this.createdDate = LocalDateTime.now();
		this.modifiedDate = LocalDateTime.now();
		this.effectiveDate = LocalDateTime.now();
	}
	
	public Phone (String pentityId, String pstatus, String ptype, String pnumber, String pdesc, LocalDateTime effective) {
		this.entityId = pentityId;
		this.status = pstatus;		
		this.type = ptype;
		this.contact = pnumber;
		this.description = pdesc;
		this.createdDate = LocalDateTime.now();
		this.modifiedDate = LocalDateTime.now();
		this.effectiveDate = effective;	
	}
}