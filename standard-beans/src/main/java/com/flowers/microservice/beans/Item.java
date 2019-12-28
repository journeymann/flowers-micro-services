package com.flowers.microservice.beans;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.flowers.microservice.beans.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  01/15/2018
 * @version 1.0
 * 
 * item sku class entity type definition.
 */
@Entity
@JsonRootName(value = "items")
@ApiModel(description="Item detail Information. ")
@Document(collection = "items")
@RestResource(exported = false)
public class Item extends Model implements Comparable<Item>{
	
	@ApiModelProperty(notes="Item Id", required = false)
	@Id	@GeneratedValue	private String itemId;
	@ApiModelProperty(notes="Item Name", required = true)
	@NotNull @Length(min = 1, max = 120) private String name;
	@ApiModelProperty(notes="Product Sku", required = false)
	private String sku;
	@ApiModelProperty(notes="Item Description", required = true)
	@NotNull @Length(min = 1, max = 120) private String description;
	@ApiModelProperty(notes="Price", required = false)
	private Double price;
	@ApiModelProperty(notes="Length", required = false)
	private Integer length;	
	@ApiModelProperty(notes="Width", required = false)
	private Integer width;
	@ApiModelProperty(notes="Height", required = false)
	private Integer height;
	@ApiModelProperty(notes="Quantity", required = false)
	private Long quantity;
	@ApiModelProperty(notes="Weight", required = false)
	private Double weight;
	@ApiModelProperty(notes="Product Id", required = false)
	private String productid;
	@ApiModelProperty(notes="Product Available Date", required = false)
	@Valid private LocalDate availableDate;
	@ApiModelProperty(notes="Product Start Date", required = false)
	@Valid private LocalDate startDate = LocalDate.now();
	@ApiModelProperty(notes="Product End Date", required = false)
	@Valid private LocalDate endeDate;
	
	SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-YYYY");
	
	public Item() {}
	
	public Item(String id, String name, String sku, String description, String availableDate, Double price,
			Integer length, Integer width, Integer height, Integer weight, Integer quantity, Integer productid) {
		super();
		this.itemId=id;
		this.name = name;
		this.sku = sku;
		this.description = description;
		try {
			this.availableDate = sdf.parse(availableDate).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //TODO refactor here
    	} catch (Exception pe) {
    		// suppress
    	}
		
		this.price = price;
		this.length = length;
		this.width = width;
		this.height = height;
		this.weight = Double.valueOf(weight);
		this.productid = String.valueOf(productid);
		this.quantity = Long.valueOf(quantity);
	}
	
	public Item(String productid, String name,String sku,String description,LocalDate availableDate, Double price){
		
		this.productid=productid;
		this.name=name;
		this.sku=sku;
		this.price=price;
		this.description=description;
		this.availableDate=availableDate;
		this.price=price;
	}
	
	public Item(String productid, String name, String sku,String description,LocalDate availableDate,Double price,Integer length,
			Integer width, Integer height,Double weight){
		
		this.productid=productid;
		this.name=name;
		this.sku=sku;
		this.price=price;
		this.description=description;
		this.availableDate=availableDate;
		this.price=price;
		this.length=length;
		this.width=width;
		this.height=height;		
		this.weight=weight;
	}
	
	// TODO remove adapter code here added for existing functionality 
	public void setWeight(Integer weight) {
		this.weight = Double.valueOf(weight);
	}
	public void setAvailableDate(String date) {
		this.availableDate = parseDate(date);
	}
	public void setStartDate(String date) {
		this.startDate = parseDate(date);
	}
	public void setEndeDate(String date) {
		this.endeDate = parseDate(date);
	}
	public LocalDate parseDate(String date) {

		try {
			return sdf.parse(date).toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); //TODO refactor here
		} catch (Exception pe) {
			// suppress
		}
		return null;
	}

	@Override
	public String toString(){
		
		return String.format("DATA: itemId: %s, name: %s, sku: %s, description: %s, availableDate: %s, "
				+ "price: %s, length: %s, width: %s, height: %s, weight: %s, productid: %s, quantity: %s\n ",
				itemId, name, sku, description, availableDate, price, length, width, height, weight, productid, quantity );
	}
	
	public int compareTo(Item that) {
		return this.itemId.compareTo(that.itemId);
	}

	/**
	 * @return the itemId
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
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
	 * @return the sku
	 */
	public String getSku() {
		return sku;
	}

	/**
	 * @param sku the sku to set
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
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
	 * @return the length
	 */
	public Integer getLength() {
		return length;
	}

	/**
	 * @param length the length to set
	 */
	public void setLength(Integer length) {
		this.length = length;
	}

	/**
	 * @return the width
	 */
	public Integer getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(Integer width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public Integer getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(Integer height) {
		this.height = height;
	}

	/**
	 * @return the quantity
	 */
	public Long getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the weight
	 */
	public Double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Double weight) {
		this.weight = weight;
	}

	/**
	 * @return the productid
	 */
	public String getProductid() {
		return productid;
	}

	/**
	 * @param productid the productid to set
	 */
	public void setProductid(String productid) {
		this.productid = productid;
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
	 * @return the endeDate
	 */
	public LocalDate getEndeDate() {
		return endeDate;
	}

	/**
	 * @param endeDate the endeDate to set
	 */
	public void setEndeDate(LocalDate endeDate) {
		this.endeDate = endeDate;
	}

	/**
	 * @return the sdf
	 */
	@JsonIgnore
	public SimpleDateFormat getSdf() {
		return sdf;
	}

	/**
	 * @param sdf the sdf to set
	 */
	public void setSdf(SimpleDateFormat sdf) {
		this.sdf = sdf;
	}
	
}