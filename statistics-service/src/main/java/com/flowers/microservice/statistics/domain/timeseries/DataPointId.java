package com.flowers.microservice.statistics.domain.timeseries;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

public class DataPointId implements Serializable {

	private static final long serialVersionUID = 1L;

	private String account;

	private Date date;

	public DataPointId(String account, Date date) {
		this.account = account;
		this.date = date;
	}

	public String getAccount() {
		return account;
	}

	public Date getDate() {
		return date;
	}

	@Override
	public String toString() {
		return "DataPointId{" +
				"account='" + account + '\'' +
				", date=" + date +
				'}';
	}
}
