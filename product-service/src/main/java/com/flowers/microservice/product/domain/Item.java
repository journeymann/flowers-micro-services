/**
 * 
 */
package com.flowers.microservice.product.domain;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;

@Document(collection = "items")
public class Item extends Model{

	public Item() {};
	
	public Item(String id, String name, String sku, String description, String availableDate, Double price,
			Integer length, Integer width, Integer height, Integer weight, Integer quantity, Integer productid) {
		super();
		this.id = id;
		this.name = name;
		this.sku = sku;
		this.description = description;
		this.availableDate = availableDate;
		this.unitprice = price;
		this.length = length;
		this.width = width;
		this.height = height;
		this.weight = weight;
		this.productid = productid;
		this.quantity = quantity;
	}

	@NotNull
	@Length(min = 1, max = 20)
	private String name;
	private String sku;
	private String description;
	private String availableDate;
	private Double unitprice;
	private Integer length;	
	private Integer width;
	private Integer height;
	private Integer weight;
	private Integer productid;
	private Integer quantity;
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
	 * @return the unitprice
	 */
	public Double getUnitPrice() {
		return unitprice;
	}
	/**
	 * @param unitprice the unitprice to set
	 */
	public void setUnitPrice(Double price) {
		this.unitprice = price;
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

	/**
	 * @return the productid
	 */
	public Integer getProductid() {
		return productid;
	}
	/**
	 * @param productid the productid to set
	 */
	public void setProductid(Integer productid) {
		this.productid = productid;
	}
	@Id
	@GeneratedValue
	private String id;
	
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
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item obj = (Item) o;

        return getId().equals(obj.getId());
    }
    
    @Override
    public int hashCode() {
        return getId().hashCode();
    }
    @Override
    public String toString() {
        return 	String.format("Item{id: %s,name: %s,sku: %s,description: %s,availableDate: %s,unitprice: %s,length: %s,width: %s,"
        		+ "height: %s,weight: %s,productid: %s}",id,name,sku,description,availableDate,unitprice,length,width,height,weight,productid);

    }

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}