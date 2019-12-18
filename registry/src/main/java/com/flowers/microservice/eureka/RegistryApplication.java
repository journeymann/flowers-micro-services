/**
 * 
 */
package com.flowers.microservice.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  12/11/2017
 * @version 1.0
 *
 */

@SpringBootApplication
@EnableEurekaServer
public class RegistryApplication {

    public static void main(String... args) {
        SpringApplication.run(RegistryApplication.class, args);
    }
}
