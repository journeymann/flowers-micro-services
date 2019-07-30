package com.flowers.microservice.auth.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static transient final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);	
	
	@Override
	@Autowired 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		logger.info("Defining inMemoryAuthentication (2 users)");
		auth
		.inMemoryAuthentication()

		.withUser("user").password("password")
		.roles("USER")

		.and()

		.withUser("admin").password("password")
		.roles("USER", "ADMIN")
		;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.formLogin()

		.and()

		.httpBasic().disable()
		.anonymous().disable()
		.authorizeRequests().anyRequest().authenticated()
		;
	}
}