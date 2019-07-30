package com.flowers.microservice.tax.model;

import java.util.List;
import javax.persistence.GeneratedValue;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;

import com.flowers.microservice.tax.constants.OrderStatus;
import com.flowers.microservice.tax.model.Address;
import com.flowers.microservice.tax.model.Customer;
import com.flowers.microservice.tax.model.billing.Billing;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  01/15/2018
 * @version 1.0
 * 
 * order class entity type definition.
 */

@Document(collection = "orders")
@RestResource(exported = false)
public class Order extends Model implements Comparable<Order>{

	private String orderNum;
	private List<String> itemIdList;
	private String deliveryDate;
	private String shippingDate;
	private String status;	
	private Double orderTotal;
	private Double taxAmount;
	private String orderDate;
	private Address address;
	private Customer customer;
	private Billing billing;

	public Order(){};
	
	public Order( String orderNum, List<String> itemIdList, String deliveryDate,
			String shippingDate, Double orderTotal, Double taxAmount, String orderDate){

		this.orderNum= orderNum;
		this.itemIdList=itemIdList;
		this.deliveryDate=deliveryDate;
		this.shippingDate=shippingDate;
		this.orderTotal=orderTotal;
		this.taxAmount=taxAmount;		
		this.orderDate=orderDate;
		this.status= OrderStatus.DEFAULT_STATUS;;
		this.address = new Address();
		this.customer=new Customer();
		this.billing=new Billing();
	};

	public Order( String orderNum, List<String> itemIdList, String deliveryDate,
			String shippingDate, Double orderTotal, Double taxAmount, String orderDate, Address address, Customer customer, Billing billing){

		this.orderNum= orderNum;
		this.itemIdList=itemIdList;
		this.deliveryDate=deliveryDate;
		this.shippingDate=shippingDate;
		this.orderTotal=orderTotal;
		this.taxAmount=taxAmount;		
		this.orderDate=orderDate;
		this.address = address;
		this.billing=billing;
		this.status= OrderStatus.DEFAULT_STATUS;;		
		
	};
	
	public Order( String orderNum, List<String> itemIdList, String deliveryDate,
			String shippingDate, Double orderTotal, Double taxAmount, String orderDate, Address address, Customer customer, Billing billing, String status){

		this.orderNum= orderNum;
		this.itemIdList=itemIdList;
		this.deliveryDate=deliveryDate;
		this.shippingDate=shippingDate;
		this.orderTotal=orderTotal;
		this.taxAmount=taxAmount;		
		this.orderDate=orderDate;
		this.address = address;
		this.billing=billing;
		this.status= status;		
		
	};

	/**
	 * @return the orderNum
	 */
	@Id
	@GeneratedValue
	public String getOrderNum() {
		return orderNum;
	}

	/**
	 * @param orderNum the orderNum to set
	 */
	public void setOrderNum(String orderNum) {
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

	/**
	 * @return the orderDate
	 */
	public String getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(Address address) {
		this.address = address;
	}
	
	/**
	 * @return the customer
	 */
	public Customer getCustomer() {
		return customer;
	}

	/**
	 * @param customer the customer to set
	 */
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	/**
	 * @return the billing
	 */
	public Billing getBilling() {
		return billing;
	}

	/**
	 * @param billing the billing to set
	 */
	public void setBilling(Billing billing) {
		this.billing = billing;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString(){
	
		return String.format("orderNum: %s, itemIdList: %s, deliveryDate: %s, "
				+ "shippingDate: %s, orderTotal: %s, taxAmount: %s, orderdate: %s, address: %s \n", 
					orderNum, itemIdList, deliveryDate, shippingDate, 
					orderTotal, taxAmount, orderDate, address);
	}
	
	@Override
	public int compareTo(Order that) {
		return this.getOrderNum().compareTo(that.getOrderNum());
	}
	
}
