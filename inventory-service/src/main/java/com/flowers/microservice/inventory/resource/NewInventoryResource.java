package com.flowers.microservice.inventory.resource;

import org.hibernate.validator.constraints.URL;

import java.net.URI;
/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
public class NewInventoryResource {
    @URL
    public URI product;
}