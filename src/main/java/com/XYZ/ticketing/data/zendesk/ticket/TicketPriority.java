package com.XYZ.ticketing.data.zendesk.ticket;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonValue;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public enum TicketPriority {

	NONE(0), LOW(1), NORMAL(2), HIGH(3), URGENT(4);

	private final int id;

	private TicketPriority(int id) {

		this.id = id;
	}

	@JsonValue
	public int getId() {

		return id;
	}

	@JsonCreator
	public static TicketPriority getById(int id) {

		for (TicketPriority priority : values()) {
			if (priority.getId() == id) { return priority; }
		}

		return null;
	}

	public static class Deserializer extends JsonDeserializer<TicketPriority> {

		@Override
		public TicketPriority deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {

			return Enum.valueOf(TicketPriority.class, jp.getText().toUpperCase());
		}
	}

	public static class Serializer extends JsonSerializer<TicketPriority> {

		@Override
		public void serialize(TicketPriority ticketPriority, JsonGenerator generator, SerializerProvider provider) throws IOException {

			generator.writeString(ticketPriority.name().toLowerCase());

		}

	}

}
