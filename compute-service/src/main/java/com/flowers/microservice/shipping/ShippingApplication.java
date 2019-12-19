/**
 * 
 */
package com.flowers.microservice.shipping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

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

}