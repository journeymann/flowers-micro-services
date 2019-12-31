package com.flowers.microservice.statistics.domain.timeseries;

import com.flowers.microservice.statistics.domain.Currency;
import com.flowers.microservice.statistics.domain.TimePeriod;

import java.math.BigDecimal;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 * Represents normalized {@link com.flowers.microservice.statistics.domain.Item} object
 * with {@link Currency#getBase()} currency and {@link TimePeriod#getBase()} time period
 */
public class ItemMetric {

	private String title;

	private BigDecimal amount;

	public ItemMetric(String title, BigDecimal amount) {
		this.title = title;
		this.amount = amount;
	}

	public String getTitle() {
		return title;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ItemMetric that = (ItemMetric) o;

		return title.equalsIgnoreCase(that.title);

	}

	@Override
	public int hashCode() {
		return title.hashCode();
	}
}
