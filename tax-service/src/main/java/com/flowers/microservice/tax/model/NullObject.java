/**
 * 
 */
package com.flowers.microservice.tax.model;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  12/18/2017
 * @version 1.0
 *
 * Implementation of the Null Object design pattern  null object class entity type definition.
 * 
 * This removes the need for methods to return null which relieves a lot of headache associated 
 * with checking for nulls and reduces the possibility of NullPointerexceptions 
 *
 */
@Document(collection = "domain")
@RestResource(exported = false)
public final class NullObject extends Model implements Comparable<NullObject>{
	
	public NullObject(){
		super();
	}

	@Override
	public int compareTo(NullObject that) {
		return 0;
	}
}