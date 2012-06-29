package com.XYZ.ticketing.data.zendesk.ticket;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.joda.time.DateTime;

public class TicketComment {

	private int authorId;

	private String value;

	private boolean isPublic;

	private DateTime createdAt;
	
	private String[] uploads;

	@JsonProperty("author_id")
	public int getAuthorId() {

		return authorId;
	}

	@JsonProperty("author_id")
	public void setAuthorId(int authorId) {

		this.authorId = authorId;
	}

	@JsonIgnore()
	public DateTime getCreatedAt() {

		return createdAt;
	}

	@JsonProperty("created_at")
	public void setCreatedAt(DateTime createdAt) {

		this.createdAt = createdAt;
	}

	@JsonProperty("public")
	public boolean isPublic() {

		return isPublic;
	}

	@JsonProperty("public")
	public void setPublic(boolean isPublic) {

		this.isPublic = isPublic;
	}

	@JsonProperty("value")
	public String getValue() {

		return value;
	}

	@JsonProperty("value")
	public void setValue(String value) {

		this.value = value;
	}
	
	@JsonProperty("uploads")
	public String[] getUploads() {

		return uploads;
	}

	@JsonProperty("uploads")
	public void setUploads(String[] uploads) {

		this.uploads = uploads.clone();
	}
}
