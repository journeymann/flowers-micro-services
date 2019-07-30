/**
 * 
 */
package com.flowers.microservice.monitoring;

import com.flowers.microservice.monitoring.stream.FlowersStreamServlet;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  12/11/2017
 * @version 1.0
 *
 */
@Configuration
@ComponentScan
@EnableAutoConfiguration
@Controller
@EnableHystrixDashboard
public class MonitoringApplication extends org.springframework.boot.web.servlet.support.SpringBootServletInitializer {

	@RequestMapping("/")
	public String home() {
		return "forward:/hystrix";
	}

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MonitoringApplication.class);
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(MonitoringApplication.class).run(args);
    }

    @Bean
    public ServletRegistrationBean<FlowersStreamServlet> mockStreamServlet() {
        return new ServletRegistrationBean<FlowersStreamServlet>(new FlowersStreamServlet(), "/mock.stream");
    }
}

