package com.flowers.microservice.product.config;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
public class MongoConfig {

    @Bean
    public MongoClientOptions optionsProvider() {
        MongoClientOptions.Builder optionsBuilder = new MongoClientOptions.Builder();
        optionsBuilder.serverSelectionTimeout(10000);
        MongoClientOptions options = optionsBuilder.build();
        return options;
    }
    
}