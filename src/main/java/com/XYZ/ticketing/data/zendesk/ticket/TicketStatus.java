package com.XYZ.ticketing.data.zendesk.ticket;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.annotate.JsonValue;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public enum TicketStatus {

	NEW(0), OPEN(1), PENDING(2), SOLVED(3), CLOSED(4);

	private final int id;

	private TicketStatus(int id) {

		this.id = id;
	}

	@JsonValue
	public int getId() {

		return id;
	}

	public static TicketStatus getById(int id) {

		for (TicketStatus status : values()) {
			if (status.getId() == id) { return status; }
		}

		return null;
	}

	public static class Deserializer extends JsonDeserializer<TicketStatus> {

		@Override
		public TicketStatus deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {

			return Enum.valueOf(TicketStatus.class, jp.getText().toUpperCase());
		}
	}

	public static class Serializer extends JsonSerializer<TicketStatus> {

		@Override
		public void serialize(TicketStatus ticketStatus, JsonGenerator generator, SerializerProvider provider) throws IOException {

			generator.writeString(ticketStatus.name().toLowerCase());

		}

	}
}
