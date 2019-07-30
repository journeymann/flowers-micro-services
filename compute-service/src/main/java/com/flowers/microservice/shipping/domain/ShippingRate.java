package com.flowers.microservice.shipping.domain;

import java.util.Date;
import javax.persistence.GeneratedValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "shippingrates")
public class ShippingRate extends Model{
	
    private String srcstate;
    private String deststate;
	private Date deliveryDate;
	private Date shippingDate;
	private String method;
	private float rate;
   
    public ShippingRate(String srcstate, String deststate, Date shippingdate, Date deliverydate, String method) {
        this.srcstate = srcstate;
        this.deststate = deststate;
        this.shippingDate=shippingdate;
        this.deliveryDate=deliverydate;
        this.method=method;
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

        ShippingRate obj = (ShippingRate) o;

        return getId().equals(obj.getId());

    }
    
    @Override
    public int hashCode() {
        return getId().hashCode();
    }
    
    @Override
    public String toString() {
        return 	String.format("Shipment{id: %s,srcstate: %s,deststate: %s, shippingdate: %s, deliverydate: %s, method: %s, rate: %s}",id,srcstate,deststate, shippingDate, deliveryDate, method,rate);
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

	public void setMethod(String method) {
		this.method = method;
	}

	public String getSrcstate() {
		return srcstate;
	}

	public void setSrcstate(String srcstate) {
		this.srcstate = srcstate;
	}

	public String getDeststate() {
		return deststate;
	}

	public void setDeststate(String deststate) {
		this.deststate = deststate;
	}

	public float getRate() {
		return rate;
	}

	public void setRate(float rate) {
		this.rate = rate;
	}

	public String getMethod() {
		return method;
	}
}
