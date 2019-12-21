/**
 * 
 */
package com.flowers.microservice.tax.service;

import java.util.List;

import com.flowers.microservice.beans.Order;
import com.flowers.microservice.beans.Product;
import com.flowers.microservice.beans.TaxRate;
import com.flowers.microservice.tax.request.TaxRequest;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  12/11/2017
 * @version 1.0
 *
 */

public interface TaxRateService {
	
	public TaxRate createTaxRate(TaxRate taxRate);
	
	public TaxRate findTaxRateById(String taxdataid);
	
	public TaxRate findTaxRateByZip(String zipcode);
	
	public TaxRate findTaxRateByRequest(TaxRequest taxdata);
	
	public List<TaxRate> findAllTaxRateNamesLike(String term);
	
	public List<TaxRate> findAllTaxRates();
	
	public List<TaxRate> findAllTaxRateList();
	
	public TaxRate updateTaxRate(String taxdataid, TaxRate taxRate);
	
	public void deleteTaxRate(String taxdataid);
	
	public Double calculateOrderTax(Order order);
	
	public Double calculateOrderTax(String orderid);

	public Double calculateOrderShippingTax(String orderid);
	
	public  TaxRate findOneTaxRate(String state);
	
	public Double calculateItemTax(String itemid);
	
	public Double calculateItemTax(Product item, String zipcode);
}
