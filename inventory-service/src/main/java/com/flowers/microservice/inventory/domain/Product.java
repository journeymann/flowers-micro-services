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
import javax.validation.Valid;

import java.util.Arrays;
import java.util.List;
/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "products")
public class Product extends Model{
	
	public Product() {};

	public Product(String productId, String name, String shortDescription, String longDescription, List<Item> items) {
		super();
		this.productId = productId;
		this.name = name;
		this.shortDescription = shortDescription;
		this.longDescription = longDescription;
		this.items = items;
	}
	
	@Id
	@GeneratedValue
	private String productId;
	
	@Valid	private String name;
	private String shortDescription;
	private String longDescription;
	
	@Valid private List<Item> items;
	
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product obj = (Product) o;

        return getProductId().equals(obj.getProductId());
    }
    
    @Override
    public int hashCode() {
        return getProductId().hashCode();
    }
    @Override
    public String toString() {
        return 	String.format("Product{productId %s,name %s,shortDescription %s,longDescription %s,items %s}",productId,name,shortDescription,longDescription,Arrays.toString(items.toArray()));

    }
	
}