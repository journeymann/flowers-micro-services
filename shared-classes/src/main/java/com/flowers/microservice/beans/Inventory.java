/**
 * 
 */
package com.flowers.microservice.beans;

import org.springframework.data.annotation.Id;
/**
 * @author cgordon
 *
 */
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.flowers.microservice.constants.Location;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.validation.Valid;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
@Entity
@JsonRootName(value = "inventory")
@ApiModel(description="Item inventory information")
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "inventory")
public class Inventory extends Model{
	
	public Inventory() {};

	public Inventory(String productId, Long count) {
		super();
		this.productId = productId;
		this.count = count;
	}
	
	public Inventory(String productId, Long count, LocalDate availableDate) {
		super();
		this.productId = productId;
		this.count = count;
		this.availableDate = availableDate;
	}
	@ApiModelProperty(notes="Inventory Id", required = false)
	@Id	@GeneratedValue	private String inventoryId;
	@ApiModelProperty(notes="Product Id", required = false)
	private String productId;
	@ApiModelProperty(notes="Product Available date", required = false)
	@Valid	private LocalDate availableDate = LocalDate.now();
	@ApiModelProperty(notes="Product Start Date", required = false)
	@Valid	private LocalDate startDate = LocalDate.now();
	@ApiModelProperty(notes="Product End Date", required = false)
	@Valid	private LocalDate endDate;
	@ApiModelProperty(notes="Location ID code", required = false)
	private Location locationCode;
	@ApiModelProperty(notes="Number of products available", required = false)
	private Long count;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Inventory that = (Inventory) o;

        return this.inventoryId.equals(that.inventoryId);
    }
    
    @Override
    public int hashCode() {
        return inventoryId.hashCode();
    }
    @Override
    public String toString() {
        return 	String.format("Inventory{inventoryId: %s, productId %s,count %s,locationCode: %s, availableDate %s, startdate: %s, enddate: %s}",inventoryId,productId,count,locationCode,availableDate, startDate, endDate);

    }

	/**
	 * @return the inventoryId
	 */
	@JsonIgnore
	public String getInventoryId() {
		return inventoryId;
	}

	/**
	 * @param inventoryId the inventoryId to set
	 */
	public void setInventoryId(String inventoryId) {
		this.inventoryId = inventoryId;
	}

	/**
	 * @return the productId
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * @return the availableDate
	 */
	public LocalDate getAvailableDate() {
		return availableDate;
	}

	/**
	 * @param availableDate the availableDate to set
	 */
	public void setAvailableDate(LocalDate availableDate) {
		this.availableDate = availableDate;
	}

	/**
	 * @return the startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the locationCode
	 */
	public Location getLocationCode() {
		return locationCode;
	}

	/**
	 * @param locationCode the locationCode to set
	 */
	public void setLocationCode(Location locationCode) {
		this.locationCode = locationCode;
	}

	/**
	 * @return the count
	 */
	public Long getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(Long count) {
		this.count = count;
	}
    
	
}