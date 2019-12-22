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
import javax.persistence.GeneratedValue;
import com.flowers.microservice.beans.Model;

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
	@GeneratedValue
	private String id;
	
	@NotNull
	@Length(min = 1, max = 120)
	private String name;
	private String sku;
	private String description;
	@Valid private LocalDate availableDate;
	private Double price;
	private Integer length;	
	private Integer width;
	private Integer height;
	private Long quantity;
	private Double weight;
	private String productid;
	@Valid	private LocalDate startDate = LocalDate.now();
	@Valid	private LocalDate endeDate;
	
	SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-YYYY");
	
	public Item() {
		
	}
	
	public Item(String id, String name, String sku, String description, String availableDate, Double price,
			Integer length, Integer width, Integer height, Integer weight, Integer quantity, Integer productid) {
		super();
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
		
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
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
		
		return String.format("DATA: id: %s, name: %s, sku: %s, description: %s, availableDate: %s, "
				+ "price: %s, length: %s, width: %s, height: %s, weight: %s, productid: %s\n ",
				id, name, sku, description, availableDate, price, length, width, height, weight, productid );
	}
	
	public int compareTo(Item that) {
		return this.getProductid().compareTo(that.getProductid());
	}
	
}