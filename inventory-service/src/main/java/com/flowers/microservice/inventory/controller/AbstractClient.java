package com.flowers.microservice.inventory.controller;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */

@RefreshScope
@RestController
public abstract class AbstractClient{
	
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private EurekaClient eurekaClient;
     
    protected Object find(Long Id) {
        Application application = eurekaClient.getApplication(getSearchServiceId());
        InstanceInfo instanceInfo = application.getInstances().get(0);
        
        //String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + getTypeKey() + "/read/" + Id;
        String url = String.format("http://%s:%d/%s/read/%s",instanceInfo.getIPAddr(),instanceInfo.getPort(),getTypeKey(),Id);
        System.out.printf("URL: %s\n",url);

        return restTemplate.getForObject(url, Object.class);
    }
        
	@SuppressWarnings("unchecked")
	protected <T> List<T> findAll() {
        Application application = eurekaClient.getApplication(getSearchServiceId());
        InstanceInfo instanceInfo = application.getInstances().get(0);
        //String url = "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/" + getTypeKey() + "/all";
        String url = String.format("http://%s:%d/%s/all",instanceInfo.getIPAddr(),instanceInfo.getPort(),getTypeKey());

        System.out.printf("URL: %s\n",url);

       	return restTemplate.getForObject(url, List.class);
    }
    
    abstract String getTypeKey();
    abstract String getSearchServiceId();
    
	protected <T,R> List<R> map(@NotNull final Function<T,R> function, @NotNull final List<T> source){

		return source.stream().map(function).collect(Collectors.toList());
	}	
	
    protected <T,S> List<T> convertModelList(@NotNull final List<S> list, @NotNull final Class<T> klass) {
    	
    	return map(s -> klass.cast(s), list);
    }
}
