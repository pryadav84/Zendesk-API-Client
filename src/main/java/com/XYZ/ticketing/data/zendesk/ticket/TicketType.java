package com.XYZ.ticketing.data.zendesk.ticket;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.annotate.JsonValue;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public enum TicketType {

	NONE(0), QUESTION(1), INCIDENT(2), PROBLEM(3), TASK(4);

	private final int id;

	private TicketType(int id) {

		this.id = id;
	}

	@JsonValue
	public int getId() {

		return id;
	}

	public static TicketType getById(int id) {

		for (TicketType status : values()) {
			if (status.getId() == id) { return status; }
		}

		return null;
	}

	public static class Deserializer extends JsonDeserializer<TicketType> {

		@Override
		public TicketType deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {

			return Enum.valueOf(TicketType.class, jp.getText().toUpperCase());
		}
	}

	public static class Serializer extends JsonSerializer<TicketType> {

		@Override
		public void serialize(TicketType ticketType, JsonGenerator generator, SerializerProvider provider) throws IOException {

			generator.writeString(ticketType.name().toLowerCase());

		}

	}
}
