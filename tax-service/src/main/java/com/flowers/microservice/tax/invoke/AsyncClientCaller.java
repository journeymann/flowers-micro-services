/**
 * 
 */
package com.flowers.microservice.tax.invoke;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.AsyncInvoker;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;

import com.flowers.microservice.tax.security.SecurityTools;
import com.flowers.microservice.tax.model.Model;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  12/11/2017
 * @version 1.0
 *
 */
public class AsyncClientCaller {

	private static transient final Logger logger = LoggerFactory.getLogger(AsyncClientCaller.class);
	
	public static Model retreiveModel(String target_url, Class<?> klass) throws InterruptedException, ExecutionException, WebApplicationException, IOException{ 
		
		if(!SecurityTools.hasToken(target_url)) 
			throw new UnauthorizedUserException("You must pass user token to authenticate!");
		
		Client client = ClientBuilder.newBuilder().build();
		WebTarget webTarget = client.target(target_url);
		Invocation.Builder request = webTarget.request();
		AsyncInvoker asyncInvoker = request.async();
		Future<Response> futureResp = asyncInvoker.get();
		
		logger.info("blocking until server processes async request ...");
		Response response = futureResp.get(); //blocks until client responds or times out
		
		JsonDeserialzer reader = new JsonDeserialzer();
		
		return reader.readModelFrom(response.readEntity(String.class), klass);	
	}	
	
	public static List<Model> retreiveCollection(String target_url, Class<?> klass) throws InterruptedException, ExecutionException, WebApplicationException, IOException{ 
		
		if(!SecurityTools.hasToken(target_url)) 
			throw new UnauthorizedUserException("You must pass user token to authenticate!");
		
		Client client = ClientBuilder.newBuilder().build();
		WebTarget webTarget = client.target(target_url);
		Invocation.Builder request = webTarget.request();
		AsyncInvoker asyncInvoker = request.async();
		Future<Response> futureResp = asyncInvoker.get();
		
		logger.info("blocking until server processes async request ...");
		Response response = futureResp.get(); //blocks until client responds or times out
		
		JsonDeserialzer reader = new JsonDeserialzer();
		
		return reader.readModelListFrom(response.readEntity(String.class), klass);
	}
	
}
