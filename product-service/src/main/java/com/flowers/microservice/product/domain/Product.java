/**
 * 
 */
package com.flowers.microservice.product.domain;

/**
 * @author cgordon
 *
 */
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.flowers.microservice.product.constants.Constants;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;

import java.util.List;

@Data
@Document(collection = "products")
public class Product {

	@Id
	@GeneratedValue
	private Integer productId;
	
	@Valid	private String name;
	private String shortDescription;
	private String longDescription;
	
	@Pattern(regexp=Constants.REGEXP_VALID_BOOLFLAG, message ="Valid value for this field is either Y or N")
	private boolean active=true;	
	
	@Valid private List<Item> items;
	
	/**
	 * @return the productId
	 */
	public Integer getProductId() {
		return productId;
	}
	/**
	 * @param productId the productId to set
	 */
	public void setProductId(Integer productId) {
		this.productId = productId;
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
	 * @return the shortDescription
	 */
	public String getShortDescription() {
		return shortDescription;
	}
	/**
	 * @param shortDescription the shortDescription to set
	 */
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	/**
	 * @return the longDescription
	 */
	public String getLongDescription() {
		return longDescription;
	}
	/**
	 * @param longDescription the longDescription to set
	 */
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	/**
	 * @return the items
	 */
	public List<Item> getItems() {
		return items;
	}
	/**
	 * @param items the items to set
	 */
	public void setItems(List<Item> items) {
		this.items = items;
	}
	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}
	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}
	
}