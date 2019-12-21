package com.flowers.microservice.tax.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/28/2018
 * @version 1.0
 *
 */

@Document(collection = "users")
@RestResource(exported = false)
public class User implements UserDetails {

	private static final long serialVersionUID = 4657626537222733954L;

	@Id
	private String username;
	private String password;
	@Valid	private LocalDate startDate = LocalDate.now();
	@Valid	private LocalDate endeDate;
	
	public User(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public List<GrantedAuthority> getAuthorities() {
		return null;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
	public Date getLastPasswordReset() {
		return null;
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
	
	
}
