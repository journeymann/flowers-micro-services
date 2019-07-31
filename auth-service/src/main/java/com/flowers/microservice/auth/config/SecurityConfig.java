package com.flowers.microservice.auth.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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

		.withUser("user").password("p@ssw0rd")
		.roles("USER","TRUSTED_CLIENT")

		.and()

		.withUser("admin").password("p@ssw0rd")
		.roles("USER", "ADMIN","TRUSTED_CLIENT")
		;
	}

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
                .authorizeRequests()
                .antMatchers("/tax/**").permitAll()
                .antMatchers("/order/**").permitAll()
                .antMatchers("/product/**").permitAll()
                .antMatchers("/shipping/**").permitAll()
                .antMatchers("/dashboard/**").permitAll()
                .anyRequest().fullyAuthenticated();
        http.httpBasic().disable();
        http.anonymous().disable();
        http.csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}