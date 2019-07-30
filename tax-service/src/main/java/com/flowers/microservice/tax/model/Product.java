package com.flowers.microservice.tax.model;

import javax.persistence.GeneratedValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  12/11/2017
 * @version 1.0
 *
 */
@Document(collection = "products")
@RestResource(exported = false)
public class Product extends Model implements Comparable<Product>{

	private String productId;
	private String productName;
	private String productDescription;
	private Double price;
	
	public Product(){
		
	};
	
	public Product(String productId, String productName, String productDescription, Double price){
		
		this.productId=productId;
		this.productName=productName;
		this.productDescription=productDescription;
		this.price=price;
	};
	
	/**
	 * @return the productId
	 */
	@Id
	@GeneratedValue
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
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}
	/**
	 * @param productName the productName to set
	 */
	public void setProductName(String productName) {
		this.productName = productName;
	}
	/**
	 * @return the productDescription
	 */
	public String getProductDescription() {
		return productDescription;
	}
	/**
	 * @param productDescription the productDescription to set
	 */
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
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

	@Override
	public String toString(){
		
		return String.format("DATA: productId: %s, productName: %s, productDescription: %s, price: %s \n", productId, productName, productDescription, price);
	}
	
	@Override
	public int compareTo(Product that) {
		return this.getProductId().compareTo(that.getProductId());
	}
	
}
