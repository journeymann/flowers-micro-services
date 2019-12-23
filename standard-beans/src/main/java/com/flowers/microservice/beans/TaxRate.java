/**
 * 
 */
package com.flowers.microservice.beans;

import java.time.LocalDate;
import javax.persistence.GeneratedValue;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.rest.core.annotation.RestResource;
import com.flowers.microservice.constants.State;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  12/11/2017
 * @version 1.0
 *
 */
@Document(collection = "taxdata")
@RestResource(exported = false)
public class TaxRate extends Model implements Comparable<TaxRate>{

	@NotNull @Length(min = 1, max = 120) private String itemId;
	private String state = State.DEFAULT_STATE;
	private String county;
	private String zipcode;
	private Double rate;
	private Boolean override;
	@Valid	private LocalDate startDate = LocalDate.now();
	@Valid	private LocalDate endeDate;
	@Id @GeneratedValue private String taxRateId;
	
	public TaxRate(){};
	
	public TaxRate(String itemId, String state, String county, String zipcode, Double rate, Boolean override){
		
		this.itemId=itemId;
		this.state=state;
		this.county=county;
		this.zipcode=zipcode;
		this.override=override;		
		this.rate=rate;
	};

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaxRate that = (TaxRate) o;

        return this.taxRateId.equals(that.taxRateId);

    }
    
    @Override
    public int hashCode() {
        return taxRateId.hashCode();
    }
	@Override
	public String toString(){
		
		return String.format("TaxRate: {taxRateId: %s, itemId: %s, state: %s, county: %s, zipcode: %s, override: %s, rate: %s }\n", taxRateId, itemId, 
				state, county, zipcode, override, rate);
	}
	
	public int compareTo(TaxRate that) {
		return this.itemId.compareTo(that.itemId);
	}

	/**
	 * @return the itemId
	 */
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
	public Double getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(Double rate) {
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

	/**
	 * @return the startDate
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endeDate
	 */
	public LocalDate getEndeDate() {
		return endeDate;
	}

	/**
	 * @param endeDate the endeDate to set
	 */
	public void setEndeDate(LocalDate endeDate) {
		this.endeDate = endeDate;
	}

	/**
	 * @return the taxRateId
	 */
	public String getTaxRateId() {
		return taxRateId;
	}

	/**
	 * @param taxRateId the taxRateId to set
	 */
	public void setTaxRateId(String taxRateId) {
		this.taxRateId = taxRateId;
	}
	
}
