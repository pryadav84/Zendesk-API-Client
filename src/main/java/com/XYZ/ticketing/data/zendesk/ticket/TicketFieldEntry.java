package com.XYZ.ticketing.data.zendesk.ticket;

import org.codehaus.jackson.annotate.JsonProperty;

public class TicketFieldEntry {

	private int fieldId;

	private String value;

	@JsonProperty("id")
	public int getFieldId() {

		return fieldId;
	}

	@JsonProperty("id")
	public void setFieldId(int fieldId) {

		this.fieldId = fieldId;
	}

	@JsonProperty("value")
	public String getValue() {

		return value;
	}

	@JsonProperty("value")
	public void setValue(String value) {

		this.value = value;
	}
}
