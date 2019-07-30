/**
 * 
 */
package com.flowers.microservice.tax.invoke;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 *
 */
import javax.ws.rs.Consumes;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.flowers.microservice.tax.model.Model;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
public final class ModelMessageBodyReader implements MessageBodyReader<Model> {


	@Override
	public boolean isReadable(Class<?> type, Type genericType,
			Annotation[] annotations, MediaType mediaType) {
		return type == Model.class;
	}

	@Override
	public Model readFrom(Class<Model> type, Type genericType, Annotation[] annotations,
			MediaType mediaType, MultivaluedMap<String, String> httpHeaders,
			InputStream entityStream) throws IOException, WebApplicationException {

		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Model.class);
			return (Model) jaxbContext.createUnmarshaller().unmarshal(entityStream);
		} catch (JAXBException e) {
			throw new ProcessingException("Error deserializing Model.", e);
		}
	}
}    
