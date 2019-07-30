/**
 * 
 */
package com.flowers.microservice.shipping.domain;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
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