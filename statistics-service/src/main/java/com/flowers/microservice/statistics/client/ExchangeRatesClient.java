package com.flowers.microservice.statistics.client;

import com.flowers.microservice.statistics.domain.Currency;
import com.flowers.microservice.statistics.domain.ExchangeRatesContainer;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@FeignClient(url = "${rates.url}", name = "rates-client")
public interface ExchangeRatesClient {

	@RequestMapping(method = RequestMethod.GET, value = "/latest")
	ExchangeRatesContainer getRates(@RequestParam("base") Currency base);

}
