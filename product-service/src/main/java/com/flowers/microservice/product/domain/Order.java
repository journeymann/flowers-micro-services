/**
 * 
 */
package com.flowers.microservice.product.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@Entity
public class Order {

	private String orderNum;
	private List<String> itemIdList;
	private String deliveryDate;
	private String shippingDate;
	private Double orderTotal;
	private Double taxAmount;

	/**
	 * @return the orderNum
	 */
	@Id
	@GeneratedValue
	public String getorderNum() {
		return orderNum;
	}
	
	/**
	 * @param orderNum the orderNum to set
	 */
	public void setorderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * @return the orderNum
	 */
	public String getOrderId() {
		return orderNum;
	}

	/**
	 * @param orderNum the orderNum to set
	 */
	public void setOrderId(String orderNum) {
		this.orderNum = orderNum;
	}

	/**
	 * @return the itemIdList
	 */
	public List<String> getItemIdList() {
		return itemIdList;
	}

	/**
	 * @param itemIdList the itemIdList to set
	 */
	public void setItemIdList(List<String> itemIdList) {
		this.itemIdList = itemIdList;
	}

	/**
	 * @return the deliveryDate
	 */
	public String getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * @param deliveryDate the deliveryDate to set
	 */
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/**
	 * @return the shippingDate
	 */
	public String getShippingDate() {
		return shippingDate;
	}

	/**
	 * @param shippingDate the shippingDate to set
	 */
	public void setShippingDate(String shippingDate) {
		this.shippingDate = shippingDate;
	}

	/**
	 * @return the orderTotal
	 */
	public Double getOrderTotal() {
		return orderTotal;
	}

	/**
	 * @param orderTotal the orderTotal to set
	 */
	public void setOrderTotal(Double orderTotal) {
		this.orderTotal = orderTotal;
	}

	/**
	 * @return the taxAmount
	 */
	public Double getTaxAmount() {
		return taxAmount;
	}

	/**
	 * @param taxAmount the taxAmount to set
	 */
	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}
	
}
