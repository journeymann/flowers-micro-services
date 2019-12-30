/**
 * 
 */
package com.flowers.microservice.shipping;

import java.time.Duration;

import org.springframework.boot.CommandLineRunner;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import com.flowers.microservice.common.AbstractApplication;
import com.flowers.microservice.shipping.init.InitialiseTest;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.influx.InfluxConfig;
import io.micrometer.influx.InfluxMeterRegistry;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
@EnableEurekaClient
public class ShippingApplication extends AbstractApplication{

    @Bean
    CommandLineRunner init() {

        return args -> {
 
            
        	InitialiseTest.init(mongoTemplate);
        };

    }
	
}