package com.flowers.microservice.tax.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;
import javax.validation.constraints.NotNull;

import java.time.LocalDateTime;

import javax.persistence.GeneratedValue;
/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  01/15/2018
 * @version 1.0
 * 
 * item sku class entity type definition.
 */
@Document(collection = "items")
@RestResource(exported = false)
public class Item extends Model implements Comparable<Item>{

	@Id
	private Integer id;
	
	@NotNull
	@Length(min = 1, max = 20)
	private String name;
	private String sku;
	private String description;
	private LocalDateTime availableDate;
	private Double price;
	private Integer length;	
	private Integer width;
	private Integer height;
	private Double weight;
	private String productid;
	
	public Item(String productid, String name,String sku,String description,LocalDateTime availableDate,Double price){
		
		this.productid=productid;
		this.name=name;
		this.sku=sku;
		this.price=price;
		this.description=description;
		this.availableDate=availableDate;
		this.price=price;
	}
	
	public Item(String productid, String name, String sku,String description,LocalDateTime availableDate,Double price,Integer length,
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
		
	/**
	 * @return the id
	 */
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
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
	 * @return the availableDate
	 */
	public LocalDateTime getAvailableDate() {
		return availableDate;
	}

	/**
	 * @param availableDate the availableDate to set
	 */
	public void setAvailableDate(LocalDateTime availableDate) {
		this.availableDate = availableDate;
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

	@Override
	public String toString(){
		
		return String.format("DATA: id: %s, name: %s, sku: %s, description: %s, availableDate: %s, "
				+ "price: %s, length: %s, width: %s, height: %s, weight: %s, productid: %s\n ",
				id, name, sku, description, availableDate, price, length, width, height, weight, productid );
	}
	
	@Override
	public int compareTo(Item that) {
		return this.getProductid().compareTo(that.getProductid());
	}
	
}