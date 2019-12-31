package com.flowers.microservice.statistics.service;

import com.flowers.microservice.statistics.domain.Account;
import com.flowers.microservice.statistics.domain.timeseries.DataPoint;

import java.util.List;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

public interface StatisticsService {

	/**
	 * Finds account by given name
	 *
	 * @param accountName
	 * @return found account
	 */
	List<DataPoint> findByAccountName(String accountName);

	/**
	 * Converts given {@link Account} object to {@link DataPoint} with
	 * a set of significant statistic metrics.
	 *
	 * Compound {@link DataPoint#id} forces to rewrite the object
	 * for each account within a day.
	 *
	 * @param accountName
	 * @param account
	 */
	DataPoint save(String accountName, Account account);

}
