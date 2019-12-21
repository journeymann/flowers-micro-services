/**
 * 
 */
package com.flowers.microservice.beans;

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
public final class NullObject extends Model implements Comparable<NullObject>{
	
	public NullObject(){
		super();
	}

	public int compareTo(NullObject that) {
		return 0;
	}
}