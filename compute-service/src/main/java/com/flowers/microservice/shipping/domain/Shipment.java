package com.flowers.microservice.shipping.domain;

import java.util.Date;
import java.util.UUID;

import javax.persistence.GeneratedValue;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.flowers.microservice.shipping.constants.ShippingMethod;
/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "shipments")
public class Shipment extends Model{
	
    private String orderid;
    private String name;
	private Date deliveryDate;
	private Date shippingDate;
	private ShippingMethod method;

    public Shipment() {
        this("");
    }

    public Shipment(String name) {
        this(UUID.randomUUID().toString(), name);
    }

    public Shipment(String orderid, String name) {
        this.id = Integer.parseInt(orderid);
        this.name = name;
    }
    
    public Shipment(String orderid, String name, Date shippingdate, Date deliverydate, ShippingMethod method) {
        this.id = Integer.parseInt(orderid);
        this.name = name;
        this.shippingDate=shippingdate;
        this.deliveryDate=deliverydate;
        this.method=method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
	@Id
	@GeneratedValue
	protected Integer id;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shipment obj = (Shipment) o;

        return getId().equals(obj.getId());

    }
    
    @Override
    public int hashCode() {
        return getId().hashCode();
    }
    
    @Override
    public String toString() {
        return 	String.format("Shipment{id: %s,orderid: %s,name: %s, shippingdate: %s, deliverydate: %s, method: %s}",id,orderid,name, shippingDate, deliveryDate, method);
    }

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public void setMethod(ShippingMethod method) {
		this.method = method;
	}
}
