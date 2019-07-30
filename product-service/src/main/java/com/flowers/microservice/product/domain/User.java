/**
 * 
 */
package com.flowers.microservice.product.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

import javax.persistence.GeneratedValue;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "users")
public class User extends Model implements UserDetails{

	public User(){};
	
	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	private static final long serialVersionUID = 4657626537222733954L;

	private String username;

	private String password;

	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}

	public List<GrantedAuthority> getAuthorities() {
		return null;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return true;
	}
	@Id
	@GeneratedValue
	private String id;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User obj = (User) o;

        return getId().equals(obj.getId());

    }
    
    @Override
    public int hashCode() {
        return getId().hashCode();
    }
    @Override
    public String toString() {
        return 	String.format("User{id: %s, username: %s, password: %s}",id,username, password);

    }
 }