package com.flowers.microservice.monitoring.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */

@EnableCircuitBreaker
@ComponentScan
@Configuration
@EnableAutoConfiguration
public class AppConfig{

	@Bean
	@ConditionalOnMissingBean(AppConfigProperties.class)
	public AppConfigProperties frameworkConfigProperties() {
		return new AppConfigProperties();
	}
}