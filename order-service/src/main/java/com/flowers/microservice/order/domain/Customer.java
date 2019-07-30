package com.flowers.microservice.order.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
@Document(collection = "customers")
public class Customer extends Model{

    private String firstName;
    private String lastName;
    private String username;

    @DBRef(lazy = true)
    private List<Address> addresses = new ArrayList<>();

    @DBRef(lazy = true)
    private List<Card> cards = new ArrayList<>();

    public Customer() {
    }

    public Customer(String id, String firstName, String lastName, String username, List<Address> addresses,
                    List<Card> cards) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.addresses = addresses;
        this.cards = cards;
    }

    public Customer(String firstName, String lastName, String username, List<Address> addresses, List<Card> cards) {
        this(null, firstName, lastName, username, addresses, cards);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (!getId().equals(customer.getId())) return false;
        return getUsername() != null ? getUsername().equals(customer.getUsername()) : customer.getUsername() == null;

    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        return result;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    public String toString() {
        return 	String.format("Customer{id: %s,firstName: %s,lastName: %s,username: %s,addresses: %s,cards: %s}",id,firstName,lastName,username,addresses,cards);

    }
        
}
