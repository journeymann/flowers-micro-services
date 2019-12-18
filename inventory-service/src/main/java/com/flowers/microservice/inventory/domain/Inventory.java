/**
 * 
 */
package com.flowers.microservice.inventory.domain;

import org.springframework.data.annotation.Id;
/**
 * @author cgordon
 *
 */
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.GeneratedValue;

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
	
	@Id
	@GeneratedValue
	private String inventory;
	
	private String productId;
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