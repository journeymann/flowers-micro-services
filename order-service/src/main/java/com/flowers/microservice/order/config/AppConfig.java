package com.flowers.microservice.order.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.flowers.microservice.common.AbstractAppConfig;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */

@EntityScan(basePackages = {"com.flowers.microservice.beans"} )
@EnableMongoRepositories(basePackages = {"com.flowers.microservice.order.repository"})
public class AppConfig extends AbstractAppConfig{

	@Bean
	@ConditionalOnMissingBean(AppConfigProperties.class)
	public AppConfigProperties frameworkConfigProperties() {
		return new AppConfigProperties();
	}
}