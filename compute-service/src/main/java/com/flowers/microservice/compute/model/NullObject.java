/**
 * 
 */
package com.flowers.microservice.compute.model;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author cgordon
 * @created 12/18/2017
 * @version 1.0
 *
 * Implementation of the Null Object design pattern  null object class entity type definition.
 * 
 * This removes the need for methods to return null which relieves a lot of headache associated 
 * with checking for nulls and reduces the possibility of NullPointerexceptions 
 *
 */
@JsonRootName(value = "none")
public final class NullObject extends Model{
	
	public NullObject(){
		super();
	}

}