/**
 * 
 */
package com.flowers.microservice.beans;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.flowers.microservice.beans.Model;
import java.time.LocalDate;
import javax.persistence.GeneratedValue;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "users")
public class User extends Model{

	public User(){};
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	@NotNull @Length(min = 1, max = 120) private String username;
	private String password;
	@Valid	private LocalDate startDate = LocalDate.now();
	@Valid	private LocalDate endeDate;
	@Id @GeneratedValue String userId;

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        return this.username.equals(that.username);
    }
    
    @Override
    public int hashCode() {
        return this.username.hashCode();
    }
    
    @Override
    public String toString() {
        return 	String.format("User{userid: %s, username: %s, password: %s}",userId,username, password);

    }

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endeDate
	 */
	public LocalDate getEndeDate() {
		return endeDate;
	}

	/**
	 * @param endeDate the endeDate to set
	 */
	public void setEndeDate(LocalDate endeDate) {
		this.endeDate = endeDate;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
    
 }