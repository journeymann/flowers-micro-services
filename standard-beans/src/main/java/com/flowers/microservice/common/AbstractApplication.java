/**
 * 
 */
package com.flowers.microservice.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.data.mongodb.core.MongoTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
@SpringBootApplication
@EnableSwagger2
@EnableZuulProxy
@EnableCircuitBreaker
public abstract class AbstractApplication{

	public static void main(String[] args) {
		SpringApplication.run(AbstractApplication.class, args);
	}
	
	@Autowired
	protected MongoTemplate mongoTemplate;
		
}