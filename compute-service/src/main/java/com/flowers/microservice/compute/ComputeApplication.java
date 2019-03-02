/**
 * 
 */
package com.flowers.microservice.compute;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.flowers.microservice.compute.facade.FunctionFacade;
import com.flowers.microservice.compute.model.Product;
import com.flowers.microservice.compute.repository.FlowersProductMongoRepository;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */
@SpringBootApplication
@EntityScan(basePackages = {"com.flowers.microservice.compute.model"} )
@EnableMongoRepositories(basePackages = {"com.flowers.microservice.compute.repository"})
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class ComputeApplication{

	public static void main(String[] args) {
		SpringApplication.run(ComputeApplication.class, args);
	}

	@Bean
	CommandLineRunner init(FlowersProductMongoRepository repository) {

		return args -> {

			repository.deleteAll();

			// save a couple of Product
			repository.save(new Product("10001", "My Product Name 001", "My Product Description 001", 10.01));
			repository.save(new Product("10002", "My Product Name 002", "My Product Description 002", 10.02));
			repository.save(new Product("10003", "My Product Name 003", "My Product Description 003", 10.03));

			// fetch all Product
			System.out.println("Products found with findAll():");
			System.out.println("-------------------------------");
			System.out.println(FunctionFacade.printList(repository.findAllProducts()));
			System.out.println();

			// fetch an individual Product
			System.out.println("Products found with findByName('001'):");
			System.out.println("--------------------------------");
			System.out.println(repository.findByName("001"));
			System.out.println();			

			System.out.println("Products list found with findAllByName('002'):");
			System.out.println("--------------------------------");
			System.out.println(FunctionFacade.printList(repository.findAllByName("002")));
			System.out.println();			
	
			System.out.println("Products [EXPECT FAIL] found with findByName('00A'):");
			System.out.println("--------------------------------");
			System.out.println(repository.findByName("00A"));
			System.out.println();			
			
			System.out.println("\n\n\n\n USING QUERY EMBEDDED ANNOTAE");
			
			// fetch an individual Product
			System.out.println("Products found with findCustomByProductName('001'):");
			System.out.println("--------------------------------");
			System.out.println(repository.findCustomByProductName("001"));
			System.out.println();			

			System.out.println("Products found all with findCustomListProductName('00'):");
			System.out.println("--------------------------------");
			System.out.println(FunctionFacade.printList(repository.findCustomListProductName("00")));	
			System.out.println();			

			System.out.println("Products [EXPECT FAIL] found with findByName('00A'):");
			System.out.println("--------------------------------");
			System.out.println(repository.findCustomByProductName("00A"));
			System.out.println();			
		};
	}
}