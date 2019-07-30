package com.flowers.microservice.order.resource;

import org.hibernate.validator.constraints.URL;

import java.net.URI;
/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
public class NewOrderResource {
    @URL
    public URI customer;
    @URL
    public URI address;
    @URL
    public URI card;
    @URL
    public URI items;
    @URL
    public URI shipment;
}