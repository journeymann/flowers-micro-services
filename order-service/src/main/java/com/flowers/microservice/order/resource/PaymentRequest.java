package com.flowers.microservice.order.resource;

import com.flowers.microservice.beans.Address;
import com.flowers.microservice.beans.billing.CreditCard;
import com.flowers.microservice.beans.Customer;
/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
public class PaymentRequest {
    private Address address;
    private CreditCard card;
    private Customer customer;
    private float amount;

    public PaymentRequest() {
    }

    public PaymentRequest(Address address, CreditCard card, Customer customer, float amount) {
        this.address = address;
        this.customer = customer;
        this.card = card;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return 	String.format("PaymentRequest{address: %s, card: %s, customer: %s, amount: %s}", address, card, customer, amount);
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

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }
}