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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import javax.persistence.GeneratedValue;
import javax.validation.Valid;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
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
	
	@Id
	@GeneratedValue
	private String inventory;
	
	private String productId;
	@Valid	private LocalDate availableDate = LocalDate.now();
	@Valid	private LocalDate startDate = LocalDate.now();
	@Valid	private LocalDate endDate;

	private Long count;
	
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
	 * @return the inventory
	 */
	public String getInventory() {
		return inventory;
	}

	/**
	 * @param inventory the inventory to set
	 */
	public void setInventory(String inventory) {
		this.inventory = inventory;
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

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Inventory obj = (Inventory) o;

        return getProductId().equals(obj.getProductId());
    }
    
    @Override
    public int hashCode() {
        return getProductId().hashCode();
    }
    @Override
    public String toString() {
        return 	String.format("Inventory{productId %s,count %s}",productId,count);

    }
	
}