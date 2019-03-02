package com.flowers.microservice.statistics.repository.converter;

import com.mongodb.DBObject;
import com.flowers.microservice.statistics.domain.timeseries.DataPointId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@Component
public class DataPointIdReaderConverter implements Converter<DBObject, DataPointId> {

	@Override
	public DataPointId convert(DBObject object) {

		Date date = (Date) object.get("date");
		String account = (String) object.get("account");

		return new DataPointId(account, date);
	}
}
