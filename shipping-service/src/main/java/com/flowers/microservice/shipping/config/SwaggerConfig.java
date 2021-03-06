package com.flowers.microservice.shipping.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  12/11/2019
 * @version 1.0
 *
 * 
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket produceApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.flowers.microservice.shipping.controller"))
				.paths(paths()).build();
	}

	// Describe your apis
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Flower Store Management Rest APIs")
				.description("This page lists all the rest apis for Flower Store Management App.").version("1.0-SNAPSHOT")
				.build();
	}

	// Only select apis that matches the given Predicates.
	private Predicate<String> paths() {
		return Predicates.and(PathSelectors.regex("/.*"), Predicates.not(PathSelectors.regex("/(error|service+).*")));
	}
}