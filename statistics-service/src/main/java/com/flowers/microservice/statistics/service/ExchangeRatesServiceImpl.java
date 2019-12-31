package com.flowers.microservice.statistics.service;

import com.google.common.collect.ImmutableMap;
import com.flowers.microservice.statistics.client.ExchangeRatesClient;
import com.flowers.microservice.statistics.domain.Currency;
import com.flowers.microservice.statistics.domain.ExchangeRatesContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@Service
public class ExchangeRatesServiceImpl implements ExchangeRatesService {

	private static final Logger log = LoggerFactory.getLogger(ExchangeRatesServiceImpl.class);

	private ExchangeRatesContainer container;

	@Autowired
	private ExchangeRatesClient client;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Map<Currency, BigDecimal> getCurrentRates() {

		if (container == null || !container.getDate().equals(LocalDate.now())) {
			container = client.getRates(Currency.getBase());
			log.info("exchange rates has been updated: {}", container);
		}

		return ImmutableMap.of(
				Currency.EUR, container.getRates().get(Currency.EUR.name()),
				Currency.RUB, container.getRates().get(Currency.RUB.name()),
				Currency.USD, BigDecimal.ONE
		);
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("deprecation")
	@Override
	public BigDecimal convert(Currency from, Currency to, BigDecimal amount) {

		Assert.notNull(amount);

		Map<Currency, BigDecimal> rates = getCurrentRates();
		BigDecimal ratio = rates.get(to).divide(rates.get(from), 4, RoundingMode.HALF_UP);

		return amount.multiply(ratio);
	}
}
