/**
 * 
 */
package com.flowers.microservice.tax.model;

import javax.persistence.GeneratedValue;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;
import com.flowers.microservice.tax.constants.State;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  12/11/2017
 * @version 1.0
 *
 */
@Document(collection = "taxdata")
@RestResource(exported = false)
public class TaxRate extends Model implements Comparable<TaxRate>{

	private String itemId;
	private String state = State.DEFAULT_STATE;
	private String county;
	private String zipcode;
	private Double rate;
	private Boolean override;
	
	public TaxRate(){
		
	};
	
	public TaxRate(String itemId, String state, String county, String zipcode, Double rate, Boolean override){
		
		this.itemId=itemId;
		this.state=state;
		this.county=county;
		this.zipcode=zipcode;
		this.override=override;		
		this.rate=rate;

	};

	/**
	 * @return the itemId
	 */
	@Id
	@GeneratedValue
	public String getItemId() {
		return itemId;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the county
	 */
	public String getCounty() {
		return county;
	}

	/**
	 * @param county the county to set
	 */
	public void setCounty(String county) {
		this.county = county;
	}

	/**
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * @param zipcode the zipcode to set
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * @return the rate
	 */
	public Double getAmount() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setAmount(Double rate) {
		this.rate = rate;
	}

	/**
	 * @return the override
	 */
	public Boolean getOverride() {
		return override;
	}

	/**
	 * @param override the override to set
	 */
	public void setOverride(Boolean override) {
		this.override = override;
	}

	@Override
	public String toString(){
		
		return String.format("DATA: itemId: %s, state: %s, county: %s, zipcode: %s, override: %s, rate: %s \n", itemId, 
				state, county, zipcode, override, rate);
	}
	
	@Override
	public int compareTo(TaxRate that) {
		return this.itemId.compareTo(that.itemId);
	}
	
}
