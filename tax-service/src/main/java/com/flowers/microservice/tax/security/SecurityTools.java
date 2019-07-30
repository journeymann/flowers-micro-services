/**
 * 
 */
package com.flowers.microservice.tax.security;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.User;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/28/2018
 * @version 1.0
 *
 */
public class SecurityTools {

	private static transient final Logger logger = LoggerFactory.getLogger(SecurityTools.class);
	
	public static UserDetails buildUserDetails(String username, String password){
	
	    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
	    authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
	    return new User(username,  password, authorities);

	}	
	
	public static boolean hasToken(String url){
		
		try {
			@SuppressWarnings("deprecation")
			List<NameValuePair> params = URLEncodedUtils.parse(new URI(url), "UTF-8");
			return !params.isEmpty() && params.stream().anyMatch(x -> x.getName().equalsIgnoreCase("token"));
			
		} catch (URISyntaxException e) {
			logger.error(e.getMessage());
			return false;
		}
	}
	
	public static String getParameterValue(String url, String key){
		
		try {
			@SuppressWarnings("deprecation")
			List<NameValuePair> params = URLEncodedUtils.parse(new URI(url), "UTF-8");
			return params.stream().filter(x -> x.getName().equalsIgnoreCase(key)).findFirst().get().getValue();
			
		} catch (URISyntaxException e) {
			logger.error(e.getMessage());
			return "";
		}
	}	
	
	public static String generateToken(String url){
		 
		TokenUtils utils = new TokenUtils();
		String username=SecurityTools.getParameterValue(url, "username"); 
		String password=SecurityTools.getParameterValue(url, "password");

	    return utils.generateToken(SecurityTools.buildUserDetails(username,password));
		
	}
	
}
