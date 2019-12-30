package com.flowers.microservice.common;

import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.flowers.microservice.health.HealthIndicatorService;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import io.swagger.annotations.ApiParam;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
@RestController
@RefreshScope
@Produces({"application/json"})
@Consumes({"application/json"})
public abstract class AbstractController {
	   
    @Autowired
    protected EurekaClient eurekaClient;

	@Autowired
	protected HealthIndicatorService healthIndicatorService;
   
    @RequestMapping("/service-instances/{applicationName}")
    public List<InstanceInfo> serviceInstancesByApplicationName(
            @PathVariable String applicationName) {
        return this.eurekaClient.getApplication(applicationName).getInstances();
    }    

    @Value("${eureka.instance.instance-id}")
    protected String instanceId;
    
    @Value("${app.info.description}")
    protected String serviceInfo;    
    
    @Value("${spring.application.name}")
    protected String springApplicationName;   

    @GetMapping("/service-instances/instanceid")
    public StringBuffer getEurekaStatus() {
        return new StringBuffer("instance id: " + instanceId);
    }  
        
    @GetMapping(value = "/info")
	public String information(@ApiParam(value = "Model object", required = true)  Model model) {
	    Application application = eurekaClient.getApplication(springApplicationName);
	    List<InstanceInfo> instanceInfo = application.getInstances();
	    String hostname = instanceInfo.get(0).getHostName();
	    String port = instanceInfo.stream().map(p -> String.valueOf(p.getPort())).collect(Collectors.joining(","));
	    InstanceStatus status = healthIndicatorService.health();
	    
	    model.addAttribute("name", application.getName());
	    model.addAttribute("hostname", hostname);
	    model.addAttribute("port", port);
	    model.addAttribute("status", status);
	    model.addAttribute("info", serviceInfo);

        return "info-view";
	}	
}    