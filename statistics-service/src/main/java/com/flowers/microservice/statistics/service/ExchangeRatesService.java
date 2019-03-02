package com.flowers.microservice.statistics.service;

import com.flowers.microservice.statistics.domain.Currency;

import java.math.BigDecimal;
import java.util.Map;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

public interface ExchangeRatesService {

	/**
	 * Requests today's foreign exchange rates from a provider
	 * or reuses values from the last request (if they are still relevant)
	 *
	 * @return current date rates
	 */
	Map<Currency, BigDecimal> getCurrentRates();

	/**
	 * Converts given amount to specified currency
	 *
	 * @param from {@link Currency}
	 * @param to {@link Currency}
	 * @param amount to be converted
	 * @return converted amount
	 */
	BigDecimal convert(Currency from, Currency to, BigDecimal amount);
}
