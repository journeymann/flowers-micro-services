package com.flowers.microservice.beans;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.flowers.microservice.constants.ShippingMethod;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
@Entity
@JsonRootName(value = "shippingrate")
@ApiModel(description="Shipping Rate Information. ")
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "shippingrates")
public class ShippingRate extends Model{
	
	@ApiModelProperty(notes="Source state in shipping rate calculation", required = false)
    private String srcstate="none";
	@ApiModelProperty(notes="Destination state in shipping rate calculation", required = true)
    @NotNull @Length(min = 1, max = 120) private String deststate="na";
	@ApiModelProperty(notes="estimated delivery date", required = true)
	@Valid private LocalDate deliveryDate = LocalDate.now().plusDays(3);
	@ApiModelProperty(notes="expeted shipping date", required = false)
	private LocalDate shippingDate = LocalDate.now().plusDays(1);
	@ApiModelProperty(notes="shipping carrier method", required = false)
	private String method = ShippingMethod.DEFAULT;
	@ApiModelProperty(notes="calculated shipping rate", required = false)
    private float rate = 0F;
	@Id	@GeneratedValue private String shipmentRateId;
   
    public ShippingRate(String srcstate, String deststate, LocalDate shippingdate, LocalDate deliverydate, String method) {
        this.srcstate = srcstate;
        this.deststate = deststate;
        this.shippingDate=shippingdate;
        this.deliveryDate=deliverydate;
        this.method=method;
    }
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShippingRate that = (ShippingRate) o;

        return this.shipmentRateId.equals(that.shipmentRateId);

    }
    
    @Override
    @JsonIgnore
    public int hashCode() {
        return shipmentRateId.hashCode();
    }
    
    @Override
    @JsonIgnore
    public String toString() {
        return 	String.format("ShipmentRate{shipmentRateId: %s,srcstate: %s,deststate: %s, shippingdate: %s, deliverydate: %s, method: %s, rate: %s}",shipmentRateId,srcstate,deststate, shippingDate, deliveryDate, method,rate);
    }

	/**
	 * @return the srcstate
	 */
	public String getSrcstate() {
		return srcstate;
	}

	/**
	 * @param srcstate the srcstate to set
	 */
	public void setSrcstate(String srcstate) {
		this.srcstate = srcstate;
	}

	/**
	 * @return the deststate
	 */
	public String getDeststate() {
		return deststate;
	}

	/**
	 * @param deststate the deststate to set
	 */
	public void setDeststate(String deststate) {
		this.deststate = deststate;
	}

	/**
	 * @return the deliveryDate
	 */
	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * @param deliveryDate the deliveryDate to set
	 */
	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/**
	 * @return the shippingDate
	 */
	public LocalDate getShippingDate() {
		return shippingDate;
	}

	/**
	 * @param shippingDate the shippingDate to set
	 */
	public void setShippingDate(LocalDate shippingDate) {
		this.shippingDate = shippingDate;
	}

	/**
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the rate
	 */
	public float getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(float rate) {
		this.rate = rate;
	}

	/**
	 * @return the shipmentRateId
	 */
	@JsonIgnore
	public String getShipmentRateId() {
		return shipmentRateId;
	}

	/**
	 * @param shipmentRateId the shipmentRateId to set
	 */
	public void setShipmentRateId(String shipmentRateId) {
		this.shipmentRateId = shipmentRateId;
	}
    
}
