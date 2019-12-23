package com.flowers.microservice.beans;

import java.time.LocalDateTime;
import java.util.regex.Pattern;

import javax.persistence.GeneratedValue;
import org.springframework.data.annotation.Id;
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
	private LocalDateTime effectiveDate;
	@Id @GeneratedValue String emailId;
	
	public EmailAddress() {};

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
		this.email = pemail;
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

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailAddress that = (EmailAddress) o;

        return this.email.equals(that.email);
    }
    
    @Override
    public int hashCode() {
        return email.hashCode();
    }

    @Override
    public String toString() {
        return 	String.format("EmailAddress{ emailId: %s, status: %s, type: %s, email: %s, desc: %s, effectivedate: %s}",emailId, status, type, email, description, effectiveDate);

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
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the emailregex
	 */
	public static String getEmailregex() {
		return emailRegex;
	}
    
    
}