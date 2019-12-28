/**
 * 
 */
package com.flowers.microservice.beans;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.LuhnCheck;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.flowers.microservice.beans.Item;
import com.flowers.microservice.beans.Model;
import com.flowers.microservice.beans.Shipment;
import com.flowers.microservice.beans.billing.CreditCard;
import com.flowers.microservice.beans.contact.Address;
import com.flowers.microservice.beans.contact.Customer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
//curl -XPOST -H 'Content-type: application/json' http://localhost:8082/orders -d '{"customer":
//"http://localhost:8080/customer/1", "address": "http://localhost:8080/address/1", "card":
//"http://localhost:8080/card/1", "items": "http://localhost:8081/carts/1/items"}'

//curl http://localhost:8082/orders/search/customerId\?custId\=1
@Entity
@JsonRootName(value = "shippingrate")
@ApiModel(description="Shipping Rate Information. ")
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "orderitems")
public class OrderItem extends Model{

	public OrderItem() {};
	
	public OrderItem(String orderNum, List<Item> itemIdList, Shipment shipment, Float orderTotal,
			Float taxAmount) {
		super();
		this.orderNum = orderNum;
		this.itemIdList = itemIdList;
		this.shipment = shipment;
		this.orderTotal = orderTotal;
		this.taxAmount = taxAmount;
	}

	public OrderItem(String orderNum, String customerId, Customer customer, Address address,CreditCard card, List<Item> itemIdList,
			Shipment shipment, Float orderTotal,Float taxAmount, Float unitPrice, Long quantity) {
		super();
		this.orderNum = orderNum;
		this.itemIdList = itemIdList;
		this.orderTotal = orderTotal;
		this.taxAmount = taxAmount;
		this.customer=customer;
		this.address=address;
		this.card=card;
		this.customerId=customerId;
		this.shipment=shipment;
		this.unitPrice=unitPrice;
		this.quantity=quantity;
	}
	
	public OrderItem(String orderNum, String customerId, Customer customer, Address address,CreditCard card, List<Item> itemIdList,
			Shipment shipment, Float orderTotal,Float taxAmount) {
		super();
		this.orderNum = orderNum;
		this.itemIdList = itemIdList;
		this.orderTotal = orderTotal;
		this.taxAmount = taxAmount;
		this.customer=customer;
		this.address=address;
		this.card=card;
		this.customerId=customerId;
		this.shipment=shipment;

	}

	@ApiModelProperty(notes="Order Itemm Id", required = true)
	@Id	@GeneratedValue	@NotNull @Length(min = 1, max = 120) private String orderItemId;
	@ApiModelProperty(notes="Order Itemm List", required = false)
	@DBRef(lazy = true) private List<Item> itemIdList;
	@ApiModelProperty(notes="Order Total", required = false)
	private Float orderTotal;
	@ApiModelProperty(notes="Order Tax amount", required = false)
	private Float taxAmount;
	@ApiModelProperty(notes="Order Custoimer Id", required = true)
	@NotNull @Length(min = 1, max = 120) private String customerId;
	@ApiModelProperty(notes="Order Number Id", required = true)
	@NotNull @Length(min = 1, max = 120) private String orderNum;
	@ApiModelProperty(notes="Order Customer", required = false)
    private Customer customer;
	@ApiModelProperty(notes="Order Address", required = false)
    private Address address;
	@ApiModelProperty(notes="Order Credit Card", required = true)
    @NotNull @LuhnCheck private CreditCard card;
	@ApiModelProperty(notes="Order Unit Price", required = true)
    @NotNull private Float unitPrice;
	@ApiModelProperty(notes="Order quantity", required = true)
    @NotNull private Long quantity;
	@ApiModelProperty(notes="Order Shipment", required = false)
    private Shipment shipment;
	@ApiModelProperty(notes="Order Delivery Date", required = false)
	@Valid	private LocalDate deliveryDate;
	@ApiModelProperty(notes="Order Shipping Date", required = false)
	@Valid	private LocalDate shippingDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderItem obj = (OrderItem) o;

        return orderItemId.equals(obj.orderItemId);

    }
    
    @Override
    public int hashCode() {
        return orderItemId.hashCode();
    }
    @Override
    public String toString() {
        return 	String.format("OrderItem{orderItemId: %s,orderNum: %s,itemIdList: %s,deliveryDate: %s,shippingDate: %s,orderTotal: %s,taxAmount: %s, customer: %s, addesss: %s, shipment: %s}",
        		orderItemId,orderNum,Arrays.toString(itemIdList.toArray()),deliveryDate,shippingDate,orderTotal,taxAmount, customer, address, shipment);
    }

	/**
	 * @return the orderItemId
	 */
	public String getOrderItemId() {
		return orderItemId;
	}

	/**
	 * @param orderItemId the orderItemId to set
	 */
	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}

	/**
	 * @return the itemIdList
	 */
	public List<Item> getItemIdList() {
		return itemIdList;
	}

	/**
	 * @param itemIdList the itemIdList to set
	 */
	public void setItemIdList(List<Item> itemIdList) {
		this.itemIdList = itemIdList;
	}

	/**
	 * @return the orderTotal
	 */
	public Float getOrderTotal() {
		return orderTotal;
	}

	/**
	 * @param orderTotal the orderTotal to set
	 */
	public void setOrderTotal(Float orderTotal) {
		this.orderTotal = orderTotal;
	}

	/**
	 * @return the taxAmount
	 */
	public Float getTaxAmount() {
		return taxAmount;
	}

	/**
	 * @param taxAmount the taxAmount to set
	 */
	public void setTaxAmount(Float taxAmount) {
		this.taxAmount = taxAmount;
	}

	/**
	 * @return the customerId
	 */
	public String getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
	 * @return the card
	 */
	public CreditCard getCard() {
		return card;
	}

	/**
	 * @param card the card to set
	 */
	public void setCard(CreditCard card) {
		this.card = card;
	}

	/**
	 * @return the unitPrice
	 */
	public Float getUnitPrice() {
		return unitPrice;
	}

	/**
	 * @param unitPrice the unitPrice to set
	 */
	public void setUnitPrice(Float unitPrice) {
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

	/**
	 * @return the shipment
	 */
	public Shipment getShipment() {
		return shipment;
	}

	/**
	 * @param shipment the shipment to set
	 */
	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
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
    
}
