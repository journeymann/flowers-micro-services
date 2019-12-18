/**
 * 
 */
package com.flowers.microservice.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */

@SpringBootApplication
@EnableConfigServer
@EnableDiscoveryClient
public class ConfigApplication{

	public static void main(String[] args) {
		SpringApplication.run(ConfigApplication.class, args);
	}
}