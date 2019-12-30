package com.flowers.microservice.shipping.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.flowers.microservice.shipping.config.AppConfigProperties;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */

@EnableEurekaClient
@ComponentScan
@Configuration
@EnableAutoConfiguration
@EnableCircuitBreaker
@EnableOAuth2Client
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EntityScan(basePackages = {"com.flowers.microservice.beans"} )
@EnableMongoRepositories(basePackages = {"com.flowers.microservice.shipping.repository"})
@ComponentScan
public class AppConfig  {
  
    @Bean
    @ConditionalOnMissingBean(AppConfigProperties.class)
    public AppConfigProperties frameworkConfigProperties() {
        return new AppConfigProperties();
    }
    
	@Bean
	@LoadBalanced
	public WebClient.Builder loadBalancedWebClientBuilder() {
		return WebClient.builder();
	}
		
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

}