package com.flowers.microservice.beans;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;

import com.flowers.microservice.constants.Status;


/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  01/15/2018
 * @version 1.0
 * 
 * email address class entity type definition.
 */

@Document(collection = "emails")
@RestResource(exported = false)
public final class EmailAddress extends Contact{
	
    private static final String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@" + 
            "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"; 
	private String email;
	
	protected EmailAddress(){
		super();
	}
	
	public EmailAddress (String pemail, String ptype) {
		this.status = Status.DEFAULT_STATUS;		
		this.type = ptype;
		this.email = pemail;
		this.description = "";
		this.createdDate = LocalDateTime.now();
		this.modifiedDate = LocalDateTime.now();
		this.effectiveDate = LocalDateTime.now();

	}
	
	public EmailAddress (String pentityId, String ptype, String pemail) {
		this.entityId = pentityId;
		
		this.status = Status.DEFAULT_STATUS;		
		this.type = ptype;
		this.email = pemail;
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
	
    public static boolean isValidEmail(String email) 
    { 

                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (email == null) 
            return false; 
        return pat.matcher(email).matches(); 
    }

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	} 

}