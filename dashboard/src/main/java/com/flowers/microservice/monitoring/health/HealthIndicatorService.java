/**
 * 
 */
package com.flowers.microservice.monitoring.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.HealthAggregator;
import org.springframework.cloud.netflix.eureka.EurekaHealthCheckHandler;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  12/11/2017
 * @version 1.0
 *
 */
@Service
public class HealthIndicatorService {
    
	@Autowired
	ApplicationContext applicationContext;
	
	public InstanceStatus health(){
		EurekaHealthCheckHandler eurekaHandler = new EurekaHealthCheckHandler((HealthAggregator) applicationContext);
		
		return eurekaHandler.getStatus(InstanceStatus.UP);
	};

}
