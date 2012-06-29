package com.XYZ.ticketing.data.zendesk.serialize;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public final class DateTimeUtil {

	private static final DateTimeFormatter DATE_TIME_FORMAT = DateTimeFormat
			.forPattern("yyyy/MM/dd HH:mm:ss Z");

	private DateTimeUtil() {
	}

	public static DateTime parseDateTime(String text) {
		return DATE_TIME_FORMAT.parseDateTime(text)
				.toDateTime(DateTimeZone.UTC);
	}

	public static String formatDateTime(DateTime dateTime) {
		return DATE_TIME_FORMAT.print(dateTime);
	}
}
