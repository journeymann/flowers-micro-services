package com.flowers.microservice.product.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
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
@EnableMongoRepositories(basePackages = {"com.flowers.microservice.product.repository"})
@ComponentScan
public class AppConfig  {
  
    @Bean
    @ConditionalOnMissingBean(AppConfigProperties.class)
    public AppConfigProperties frameworkConfigProperties() {
        return new AppConfigProperties();
    }
}