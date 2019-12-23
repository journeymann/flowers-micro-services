package com.flowers.microservice.product.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import com.mongodb.MongoClientOptions;
/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
@Configuration
@AutoConfigureBefore(MongoAutoConfiguration.class)
@EnableMongoRepositories(basePackages = "com.flowers.microservice.product.repository")
public class MongoConfig {

    @Value("${app.mongo.timeout}")
    private Integer timeout = 10000;
    
    @Bean
    public MongoClientOptions optionsProvider() {
        MongoClientOptions.Builder optionsBuilder = new MongoClientOptions.Builder();
        optionsBuilder.serverSelectionTimeout(timeout);
        MongoClientOptions options = optionsBuilder.build();
        return options;
    }
    
}