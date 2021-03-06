package com.flowers.microservice.beans;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.validation.Valid;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;

import com.flowers.microservice.constants.OrderStatus;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.flowers.microservice.beans.OrderItem;
import com.flowers.microservice.beans.billing.Billing;
import com.flowers.microservice.beans.contact.Address;
import com.flowers.microservice.beans.contact.Customer;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  01/15/2018
 * @version 1.0
 * 
 * order class entity type definition.
 */
@Entity
@JsonRootName(value = "orders")
@ApiModel(description="Orders Information. ")
@Document(collection = "orders")
@RestResource(exported = false)
public class Order extends Model implements Comparable<Order>{

	@ApiModelProperty(notes="Order Number", required = false)
	@Id	@GeneratedValue	@Valid	@Length(min = 1, max = 120)	private String orderNum;
	@ApiModelProperty(notes="Status", required = false)
	private String status;	
	@ApiModelProperty(notes="Order Total", required = false)
	private Double orderTotal;
	@ApiModelProperty(notes="Order Tax Amount", required = false)
	private Double taxAmount;
	@ApiModelProperty(notes="Order Address", required = false)
	private Address address;
	@ApiModelProperty(notes="Order Customer", required = false)
	private Customer customer;
	@ApiModelProperty(notes="Order Billing Info", required = false)
	private Billing billing;
	@ApiModelProperty(notes="Order Item List", required = false)
	@DBRef(lazy = true) private List<OrderItem> itemList;
	@ApiModelProperty(notes="Order Date", required = false)
	@Valid	private LocalDate orderDate = LocalDate.now();
	@ApiModelProperty(notes="Order DElivery Date", required = false)
	@Valid	private LocalDate deliveryDate;
	@ApiModelProperty(notes="Order Shipping", required = false)
	@Valid	private LocalDate shippingDate;
	@ApiModelProperty(notes="Unit Item Price", required = false)
	private Double unitPrice;
	@ApiModelProperty(notes="Order Quantity", required = false)
	private Long quantity;

	public Order(){};
	
	public Order( String orderNum, List<OrderItem> itemList, LocalDate deliveryDate,
			LocalDate shippingDate, Double orderTotal, Double taxAmount, LocalDate orderDate, Double unitPrice, Long quantity){

		this.orderNum= orderNum;
		this.itemList=itemList;
		this.deliveryDate=deliveryDate;
		this.shippingDate=shippingDate;
		this.orderTotal=orderTotal;
		this.taxAmount=taxAmount;		
		this.orderDate=orderDate;
		this.status= OrderStatus.DEFAULT_STATUS;;
		this.address = new Address();
		this.customer=new Customer();
		this.billing=new Billing();
		this.unitPrice=unitPrice;
		this.quantity=quantity;
	};

	public Order( String orderNum, List<OrderItem> itemList, LocalDate deliveryDate,
			LocalDate shippingDate, Double orderTotal, Double taxAmount, LocalDate orderDate, Address address, Customer customer, Billing billing,  Double unitPrice, Long quantity){

		this.orderNum= orderNum;
		this.itemList=itemList;
		this.deliveryDate=deliveryDate;
		this.shippingDate=shippingDate;
		this.orderTotal=orderTotal;
		this.taxAmount=taxAmount;		
		this.orderDate=orderDate;
		this.address = address;
		this.billing=billing;
		this.status= OrderStatus.DEFAULT_STATUS;;		
		
	};
	
	public Order( String orderNum, List<OrderItem> itemList, LocalDate deliveryDate,
			LocalDate shippingDate, Double orderTotal, Double taxAmount, LocalDate orderDate, Address address, Customer customer, Billing billing, String status){

		this.orderNum= orderNum;
		this.itemList=itemList;
		this.deliveryDate=deliveryDate;
		this.shippingDate=shippingDate;
		this.orderTotal=orderTotal;
		this.taxAmount=taxAmount;		
		this.orderDate=orderDate;
		this.address = address;
		this.billing=billing;
		this.status= status;		
		
	};

	@Override
	public String toString(){
	
		return String.format("orderNum: %s, itemIdList: %s, deliveryDate: %s, "
				+ "shippingDate: %s, orderTotal: %s, taxAmount: %s, orderdate: %s, address: %s, status: %s, billing: %s, customer: %s, unitprice: %s, quantity: %s \n", 
					orderNum, Arrays.toString(itemList.toArray()), deliveryDate, shippingDate, orderTotal, taxAmount, orderDate, address, status, billing, customer, unitPrice, quantity);
	}
	
	public int compareTo(Order that) {
		return this.orderNum.compareTo(that.orderNum);
	}

	/**
	 * @return the orderNum
	 */
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
	 * @return the itemList
	 */
	public List<OrderItem> getItemList() {
		return itemList;
	}

	/**
	 * @param itemList the itemList to set
	 */
	public void setItemList(List<OrderItem> itemList) {
		this.itemList = itemList;
	}

	/**
	 * @return the orderDate
	 */
	public LocalDate getOrderDate() {
		return orderDate;
	}

	/**
	 * @param orderDate the orderDate to set
	 */
	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	/**
	 * @return the deliveryDate
	 */
	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	/**
	 * @param deliveryDate the deliveryDate to set
	 */
	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	/**
	 * @return the shippingDate
	 */
	public LocalDate getShippingDate() {
		return shippingDate;
	}

	/**
	 * @param shippingDate the shippingDate to set
	 */
	public void setShippingDate(LocalDate shippingDate) {
		this.shippingDate = shippingDate;
	}

	/**
	 * @return the unitPrice
	 */
	public Double getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
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
	
}
