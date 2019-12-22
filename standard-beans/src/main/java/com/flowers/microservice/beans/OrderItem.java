/**
 * 
 */
package com.flowers.microservice.beans;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import javax.persistence.GeneratedValue;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.LuhnCheck;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.flowers.microservice.beans.Address;
import com.flowers.microservice.beans.Customer;
import com.flowers.microservice.beans.Item;
import com.flowers.microservice.beans.Model;
import com.flowers.microservice.beans.Shipment;
import com.flowers.microservice.beans.billing.CreditCard;

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

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "orderitems")
public class OrderItem extends Model{

	public OrderItem() {};
	
	public OrderItem(String orderNum, List<Item> itemIdList, Shipment shipment, float orderTotal,
			float taxAmount) {
		super();
		this.orderNum = orderNum;
		this.itemIdList = itemIdList;
		this.shipment = shipment;
		this.orderTotal = orderTotal;
		this.taxAmount = taxAmount;
	}

	public OrderItem(String orderNum, String customerId, Customer customer, Address address,CreditCard card, List<Item> itemIdList,
			Shipment shipment, float orderTotal,float taxAmount, Double unitPrice, Long quantity) {
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
			Shipment shipment, float orderTotal,float taxAmount) {
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
	
	@NotNull @Length(min = 1, max = 120) private String orderNum;
	private List<Item> itemIdList;
	private float orderTotal;
	private float taxAmount;
	@NotNull @Length(min = 1, max = 120) private String customerId;
    private Customer customer;
    private Address address;
    @NotNull @LuhnCheck private CreditCard card;
    @NotNull @Range(min=0L, max=100L) private Double unitPrice;
    @NotNull @Range(min=0L, max=10000L) private Long quantity;
    private Shipment shipment;
	@Valid	private LocalDate deliveryDate;
	@Valid	private LocalDate shippingDate;

	/**
	 * @return the orderNum
	 */
	
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
	 * @return the orderTotal
	 */
	public float getOrderTotal() {
		return orderTotal;
	}

	/**
	 * @param orderTotal the orderTotal to set
	 */
	public void setOrderTotal(float orderTotal) {
		this.orderTotal = orderTotal;
	}

	/**
	 * @return the taxAmount
	 */
	public float getTaxAmount() {
		return taxAmount;
	}

	/**
	 * @param taxAmount the taxAmount to set
	 */
	public void setTaxAmount(float taxAmount) {
		this.taxAmount = taxAmount;
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

        OrderItem obj = (OrderItem) o;

        return getId().equals(obj.getId());

    }
    
    @Override
    public int hashCode() {
        return getId().hashCode();
    }
    @Override
    public String toString() {
        return 	String.format("OrderItem{id: %s,orderNum: %s,itemIdList: %s,deliveryDate: %s,shippingDate: %s,orderTotal: %s,taxAmount: %s}",id,orderNum,Arrays.toString(itemIdList.toArray()),deliveryDate,shippingDate,orderTotal,taxAmount);
    }

	public String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public CreditCard getCard() {
		return card;
	}

	public void setCard(CreditCard card) {
		this.card = card;
	}

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
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
