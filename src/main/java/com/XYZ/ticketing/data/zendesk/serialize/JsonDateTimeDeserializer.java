package com.XYZ.ticketing.data.zendesk.serialize;

import java.io.IOException;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class JsonDateTimeDeserializer extends JsonDeserializer<DateTime> {

	// 2012-06-12T13:20:09Z

	@Override
	public DateTime deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext) throws IOException {

		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss'Z'");
		String date = jsonparser.getText();

		return format.parseDateTime(date);

	}

}
