package com.flowers.microservice.shipping.domain;

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
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "taxrates")
public class TaxRate extends Model{
	
    private String taxstate;
    private Float unitcostlow;
    private Float unitcosthigh;
    private Double totaltax;
    private Integer quantity;
    private Boolean taxable;
	private Float staterate;
	private Integer row;
   
    public TaxRate(Integer row, String taxstate, Float unitcostlow,Float unitcosthigh, Integer quantity, Boolean taxable, Float staterate, Double totaltax) {
        this.taxstate = taxstate;
        this.unitcostlow = unitcostlow;
        this.unitcosthigh = unitcosthigh;
        this.quantity = quantity;
        this.taxable = taxable;
        this.staterate = staterate;
        this.totaltax = totaltax;
        this.row = row;
    }

	@Id
	@GeneratedValue
	protected Integer id;
	
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTaxstate() {
		return taxstate;
	}

	public void setTaxstate(String taxstate) {
		this.taxstate = taxstate;
	}

	public Float getUnitcostlow() {
		return unitcostlow;
	}

	public void setUnitcostlow(Float unitcostlow) {
		this.unitcostlow = unitcostlow;
	}

	public Float getUnitcosthigh() {
		return unitcosthigh;
	}

	public void setUnitcosthigh(Float unitcosthigh) {
		this.unitcosthigh = unitcosthigh;
	}
	
	public Double getTotaltax() {
		return totaltax;
	}

	public void setTotaltax(Double totaltax) {
		this.totaltax = totaltax;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Boolean getTaxable() {
		return taxable;
	}

	public void setTaxable(Boolean taxable) {
		this.taxable = taxable;
	}

	public Float getStaterate() {
		return staterate;
	}

	public void setStaterate(Float staterate) {
		this.staterate = staterate;
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaxRate obj = (TaxRate) o;

        return getId().equals(obj.getId());

    }
    
    @Override
    public int hashCode() {
        return getId().hashCode();
    }
    
    @Override
    public String toString() {
        return 	String.format("Shipment{id: %s,taxstate: %s, unitcostlow: %s,unitcosthigh: %s, quantity: %s, taxable: %s, staterate: %s, totaltax: %s}",id,taxstate, unitcostlow, unitcosthigh, quantity, taxable, staterate, totaltax);
    }

	public Integer getRow() {
		return row;
	}

	public void setRow(Integer row) {
		this.row = row;
	}


}
