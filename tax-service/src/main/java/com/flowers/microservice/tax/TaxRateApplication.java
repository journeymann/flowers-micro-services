/**
 * 
 */
package com.flowers.microservice.tax;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import com.flowers.microservice.tax.health.InitialiseTest;
import com.flowers.microservice.tax.repository.FlowersTaxRateMongoRepository;
import com.flowers.microservice.tax.service.TaxRateService;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  12/11/2019
 * @version 1.0
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
@EnableZuulProxy
public class TaxRateApplication extends SpringBootServletInitializer {

	@Autowired
	TaxRateService service;		
	
	public static void main(String... args) {
		SpringApplication.run(TaxRateApplication.class, args);
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(TaxRateApplication.class);
    }
		
	@Bean
	CommandLineRunner init(FlowersTaxRateMongoRepository repository) {

		return args -> {

			InitialiseTest.init(repository, service);
		};
	}
	
}