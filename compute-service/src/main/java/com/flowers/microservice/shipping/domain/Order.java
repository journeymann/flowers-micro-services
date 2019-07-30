/**
 * 
 */
package com.flowers.microservice.shipping.domain;

import java.util.Arrays;
import java.util.List;
import javax.persistence.GeneratedValue;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@Document(collection = "orders")
public class Order extends Model{

	public Order() {};
	
	public Order(String orderNum, List<Item> itemIdList, Shipment shipment, float orderTotal,
			float taxAmount) {
		super();
		this.orderNum = orderNum;
		this.itemIdList = itemIdList;
		this.shipment = shipment;
		this.orderTotal = orderTotal;
		this.taxAmount = taxAmount;
	}

	public Order(String orderNum, String customerId, Customer customer, Address address,Card card, List<Item> itemIdList,
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
	
	private String orderNum;
	private List<Item> itemIdList;
	private String deliveryDate;
	private String shippingDate;
	private float orderTotal;
	private float taxAmount;
    private String customerId;
    private Customer customer;
    private Address address;
    private Card card;
    private Shipment shipment;

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

        Order obj = (Order) o;

        return getId().equals(obj.getId());

    }
    
    @Override
    public int hashCode() {
        return getId().hashCode();
    }
    @Override
    public String toString() {
        return 	String.format("Order{id: %s,orderNum: %s,itemIdList: %s,deliveryDate: %s,shippingDate: %s,orderTotal: %s,taxAmount: %s}",id,orderNum,Arrays.toString(itemIdList.toArray()),deliveryDate,shippingDate,orderTotal,taxAmount);
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

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public Shipment getShipment() {
		return shipment;
	}

	public void setShipment(Shipment shipment) {
		this.shipment = shipment;
	}
}
