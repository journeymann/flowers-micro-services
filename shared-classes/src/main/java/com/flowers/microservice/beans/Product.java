/**
 * 
 */
package com.flowers.microservice.beans;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
/**
 * @author cgordon
 *
 */
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
@Entity
@JsonRootName(value = "product")
@ApiModel(description="Product Information. ")
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
	
	@ApiModelProperty(notes="Product Id", required = false)
	@Id	@GeneratedValue private String productId;
	@ApiModelProperty(notes="Product Name", required = true)
	@Valid @NotNull @Length(min = 1, max = 120) private String name;
	@ApiModelProperty(notes="Product short description", required = false)
	private String shortDescription;
	@ApiModelProperty(notes="Product long description", required = false)
	private String longDescription;
	@ApiModelProperty(notes="Product start date", required = false)
	@Valid	private Date startDate = Calendar.getInstance().getTime();
	@ApiModelProperty(notes="Product available Date", required = false)
	@Valid	private Date availableDate = Calendar.getInstance().getTime();
	@ApiModelProperty(notes="Product end date", required = false)
	@Valid	private Date endeDate;
	@ApiModelProperty(notes="Product price", required = false)
	private Double price;
	@ApiModelProperty(notes="List Product Items", required = false)
	@DBRef(lazy = true) @Valid private List<Item> items;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product that = (Product) o;

        return this.productId.equals(that.productId);
    }
    
    @Override
    public int hashCode() {
        return productId.hashCode();
    }
    @Override
    public String toString() {
        return 	String.format("Product{productId %s,name %s,shortDescription %s,longDescription %s,items %s, price %s}",productId,name,shortDescription,longDescription,Arrays.toString(items.toArray()), price);

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
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the availableDate
	 */
	public Date getAvailableDate() {
		return availableDate;
	}

	/**
	 * @param availableDate the availableDate to set
	 */
	public void setAvailableDate(Date availableDate) {
		this.availableDate = availableDate;
	}

	/**
	 * @return the endeDate
	 */
	public Date getEndeDate() {
		return endeDate;
	}

	/**
	 * @param endeDate the endeDate to set
	 */
	public void setEndeDate(Date endeDate) {
		this.endeDate = endeDate;
	}

	/**
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
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
	
}