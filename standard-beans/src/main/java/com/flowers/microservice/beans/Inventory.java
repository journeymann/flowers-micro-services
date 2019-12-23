/**
 * 
 */
package com.flowers.microservice.beans;

import org.springframework.data.annotation.Id;
/**
 * @author cgordon
 *
 */
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.flowers.microservice.constants.Location;

import java.time.LocalDate;
import javax.persistence.GeneratedValue;
import javax.validation.Valid;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "inventory")
public class Inventory extends Model{
	
	public Inventory() {};

	public Inventory(String productId, Long count) {
		super();
		this.productId = productId;
		this.count = count;
	}
	
	public Inventory(String productId, Long count, LocalDate availableDate) {
		super();
		this.productId = productId;
		this.count = count;
		this.availableDate = availableDate;
	}
	
	@Id
	@GeneratedValue
	private String inventoryId;
	private String productId;
	@Valid	private LocalDate availableDate = LocalDate.now();
	@Valid	private LocalDate startDate = LocalDate.now();
	@Valid	private LocalDate endDate;
	private Location locationCode;


	private Long count;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Inventory that = (Inventory) o;

        return this.inventoryId.equals(that.inventoryId);
    }
    
    @Override
    public int hashCode() {
        return inventoryId.hashCode();
    }
    @Override
    public String toString() {
        return 	String.format("Inventory{inventoryId: %s, productId %s,count %s,locationCode: %s, availableDate %s, startdate: %s, enddate: %s}",inventoryId,productId,count,locationCode,availableDate, startDate, endDate);

    }
	
}