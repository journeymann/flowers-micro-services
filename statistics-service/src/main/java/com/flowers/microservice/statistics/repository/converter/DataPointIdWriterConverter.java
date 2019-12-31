package com.flowers.microservice.statistics.repository.converter;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.flowers.microservice.statistics.domain.timeseries.DataPointId;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@Component
public class DataPointIdWriterConverter implements Converter<DataPointId, DBObject> {

	private static final int FIELDS = 2;

	@Override
	public DBObject convert(DataPointId id) {

		DBObject object = new BasicDBObject(FIELDS);

		object.put("date", id.getDate());
		object.put("account", id.getAccount());

		return object;
	}
}
