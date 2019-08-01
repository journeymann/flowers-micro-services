/**
 * 
 */
package com.flowers.microservice.eureka.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.netflix.appinfo.ApplicationInfoManager;
import com.netflix.appinfo.EurekaInstanceConfig;
import com.netflix.appinfo.MyDataCenterInstanceConfig;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  12/11/2019
 * @version 1.0
 *
 * auto configuration file for various features available to the application
 */
@Configuration
@Component
@EnableAutoConfiguration
public class AppConfig {

	private String EUREKA_TEST_NAMESPACE="eureka";
	private ApplicationInfoManager applicationManager;
	
	@SuppressWarnings("deprecation")
	@Bean 
	public ApplicationInfoManager createApplicationManager() {
		  if (applicationManager == null) {
		    EurekaInstanceConfig instanceConfig = new MyDataCenterInstanceConfig(EUREKA_TEST_NAMESPACE) {
		      @Override
		      public String getAppname() {
		        return "discoveryClientTest";
		      }
		      @Override
		      public int getLeaseRenewalIntervalInSeconds() {
		        return 1;
		      }
		    };
		    applicationManager = new ApplicationInfoManager(instanceConfig);
		  }
		  return applicationManager;
		}
  
}
