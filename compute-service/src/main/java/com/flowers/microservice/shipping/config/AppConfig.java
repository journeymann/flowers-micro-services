package com.flowers.microservice.shipping.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
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
@EnableEurekaClient
@ComponentScan(value = "com.flowers.microservice.compute.service", useDefaultFilters = false)
@Configuration
public class AppConfig  {

    @Value("${spring.application.name}")
    private String appName;
    
    @Bean
    @ConditionalOnMissingBean(AppConfigProperties.class)
    public AppConfigProperties frameworkConfigProperties() {
        return new AppConfigProperties();
    }
}