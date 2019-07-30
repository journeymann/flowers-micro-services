/**
 * 
 */
package com.flowers.microservice.tax.request;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 *
 */
public class TaxRequest {

	private String itemId;
	private String zipcode;
	private Double amount;
	
	
	public TaxRequest(){}
	
	public TaxRequest(String itemId, String zipcode,  Double amount){
		
		this.itemId=itemId;
		this.zipcode=zipcode;
		this.amount=amount;
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
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}
	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
}
