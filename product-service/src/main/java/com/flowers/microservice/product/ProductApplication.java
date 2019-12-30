/**
 * 
 */
package com.flowers.microservice.product;

import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import com.flowers.microservice.common.AbstractApplication;
import com.flowers.microservice.product.health.InitialiseTest;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
@EnableEurekaClient
public class ProductApplication extends AbstractApplication{

    @Bean
    CommandLineRunner init() {

        return args -> {
       	    
        	InitialiseTest.init(mongoTemplate);
        };

    }
	
}