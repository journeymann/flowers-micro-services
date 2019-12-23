package com.flowers.microservice.beans;

import java.util.Date;
import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.flowers.microservice.constants.ShippingMethod;
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
	
	@NotNull @Length(min = 1, max = 120) private String orderId;
    private String name;
	private Date deliveryDate;
	private Date shippingDate;
	private ShippingMethod method;
	@Id @GeneratedValue private String shipmentId;

    public Shipment() {}

    public Shipment(String name) {
        this(UUID.randomUUID().toString(), name);
    }

    public Shipment(String orderid, String name) {
        this.orderId = orderid;
        this.name = name;
    }
    
    public Shipment(String orderid, String name, Date shippingdate, Date deliverydate, ShippingMethod method) {
        this.orderId = orderid;
        this.name = name;
        this.shippingDate=shippingdate;
        this.deliveryDate=deliverydate;
        this.method=method;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shipment that = (Shipment) o;

        return shipmentId.equals(that.shipmentId);

    }
    
    @Override
    public int hashCode() {
        return shipmentId.hashCode();
    }
    
    @Override
    public String toString() {
        return 	String.format("Shipment{shipmentId: %s,orderid: %s,name: %s, shippingdate: %s, deliverydate: %s, method: %s}",shipmentId,orderId,name, shippingDate, deliveryDate, method);
    }

	/**
	 * @return the orderId
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the deliveryDate
	 */
	public Date getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * @param deliveryDate the deliveryDate to set
	 */
	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/**
	 * @return the shippingDate
	 */
	public Date getShippingDate() {
		return shippingDate;
	}

	/**
	 * @param shippingDate the shippingDate to set
	 */
	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	/**
	 * @return the method
	 */
	public ShippingMethod getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(ShippingMethod method) {
		this.method = method;
	}

	/**
	 * @return the shipmentId
	 */
	public String getShipmentId() {
		return shipmentId;
	}

	/**
	 * @param shipmentId the shipmentId to set
	 */
	public void setShipmentId(String shipmentId) {
		this.shipmentId = shipmentId;
	}
    
}
