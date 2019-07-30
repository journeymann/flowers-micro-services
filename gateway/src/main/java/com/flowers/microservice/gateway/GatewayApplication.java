/**
 * 
 */
package com.flowers.microservice.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  12/11/2017
 * @version 1.0
 *
 * Zuul Proxy gateway Micro Service. 
 *
 *
 */
@EnableZuulProxy
@SpringBootApplication
public class GatewayApplication {

	public static void main(String... args) {
		
		try{
		SpringApplication.run(GatewayApplication.class, args);
		}catch(Exception e){
			System.out.printf("ZUUL: %s",e);
		}
	}
}