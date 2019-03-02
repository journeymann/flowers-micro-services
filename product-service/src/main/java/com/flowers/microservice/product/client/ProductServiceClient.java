/**
 * 
 */
package com.flowers.microservice.product.client;

import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.flowers.microservice.product.domain.Product;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@FeignClient(name = "product-service")
public interface ProductServiceClient {

	@RequestMapping(method = RequestMethod.POST, value = "/products", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	List<Product> readProducts();

}
