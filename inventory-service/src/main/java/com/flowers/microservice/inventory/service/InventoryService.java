/**
 * 
 */
package com.flowers.microservice.inventory.service;

import com.flowers.microservice.beans.Inventory;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */

public interface InventoryService {


	Inventory findProductCountById(String productid);
	
	Inventory updateProductCount(String productid, Long count);
	
}