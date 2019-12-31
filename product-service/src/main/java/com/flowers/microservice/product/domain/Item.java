/**
 * 
 */
package com.flowers.microservice.product.domain;

/**
 * @author cgordon
 *
 */
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;

public class Item {

	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull
	@Length(min = 1, max = 20)
	private String name;
	private String sku;
	private String description;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="mm/dd/yyyy", timezone="EST")
	private String availableDate;
	private Double price;
	private Integer length;	
	private Integer width;
	private Integer height;
	private Integer weight;
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
	public String getAvailableDate() {
		return availableDate;
	}
	/**
	 * @param availableDate the availableDate to set
	 */
	public void setAvailableDate(String availableDate) {
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
	public Integer getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
	
	
}