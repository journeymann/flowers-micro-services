/**
 * 
 */
package com.flowers.microservice.tax.invoke;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 *
 */
import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flowers.microservice.tax.model.Model;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
public final class JsonDeserialzer {

	public Object readModelFrom(InputStream entityStream, Class<?> klass) throws IOException, WebApplicationException {

		ObjectMapper mapper = new ObjectMapper();
		return (Model)mapper.readValue(entityStream, klass);
	}

	public Model readModelFrom(String jsonInString, Class<?> klass) throws JsonParseException, JsonMappingException, IOException  {

		ObjectMapper mapper = new ObjectMapper();
		return (Model)mapper.readValue(jsonInString, klass);
	}

	public List<Model> readModelListFrom(String jsonInString, Class<?> klass) throws JsonParseException, JsonMappingException, IOException  {

		System.out.printf("\nobject class name (verify): %s\n", klass);
		
		ObjectMapper mapper = new ObjectMapper();
		return  mapper.readValue(jsonInString, mapper.getTypeFactory().constructCollectionType(ArrayList.class, klass));
	}
}    
