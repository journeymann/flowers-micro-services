/**
 * 
 */
package com.flowers.microservice.shipping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.flowers.microservice.shipping.health.InitialiseTest;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class ShippingApplication{

	public static void main(String[] args) {
		SpringApplication.run(ShippingApplication.class, args);
	}
	
	@Autowired
	MongoTemplate mongoTemplate;
	
    @Bean
    CommandLineRunner init() {

        return args -> {
       	    
        	InitialiseTest.init(mongoTemplate);
        };

    }
	
}