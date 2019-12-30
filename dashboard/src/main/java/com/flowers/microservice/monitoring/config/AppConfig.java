package com.flowers.microservice.monitoring.config;

import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import com.flowers.microservice.common.AbstractAppConfig;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */

@EnableHystrixDashboard
public class AppConfig extends AbstractAppConfig{


}