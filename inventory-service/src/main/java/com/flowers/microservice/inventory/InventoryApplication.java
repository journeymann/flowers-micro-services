/**
 * 
 */
package com.flowers.microservice.inventory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import com.flowers.microservice.inventory.health.InitialiseTest;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
@SpringBootApplication
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
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