
package com.flowers.microservice.monitoring;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  12/11/2017
 * @version 1.0
 *
 */
@EnableEurekaClient
@SpringBootApplication
public class MonitoringApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MonitoringApplication.class).run(args);
    }
}

