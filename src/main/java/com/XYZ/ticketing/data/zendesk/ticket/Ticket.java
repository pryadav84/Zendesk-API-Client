package com.XYZ.ticketing.data.zendesk.ticket;

import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.joda.time.DateTime;

import com.XYZ.ticketing.data.zendesk.serialize.JsonDateTimeDeserializer;

public class Ticket {

	private Integer assigneeId;

	private DateTime createdAt;

	private String subject;

	private String description;

	private int id;

	private TicketPriority priority;

	private TicketStatus status;

	private int requesterId;

	private TicketType type;

	private DateTime updatedAt;

	private Integer problemId;

	private List<TicketComment> comments;

	private TicketComment comment;

	private List<TicketFieldEntry> entries;

	public Integer getProblemId() {

		return problemId;
	}

	public enum CustomField {
		COMPANY, DATA, SUBTYPE, CATEGORY
	}

	private HashMap<CustomField, String> customFields = new HashMap<CustomField, String>();

	@JsonProperty("problem_id")
	public void setProblemId(Integer problemId) {

		this.problemId = problemId;
	}

	@JsonIgnore()
	public Integer getAssigneeId() {

		return assigneeId;
	}

	@JsonProperty("assignee_id")
	public void setAssigneeId(Integer assigneeId) {

		this.assigneeId = assigneeId;
	}

	@JsonIgnore()
	public DateTime getCreatedAt() {

		return createdAt;
	}

	@JsonProperty("created_at")
	@JsonDeserialize(using = JsonDateTimeDeserializer.class)
	public void setCreatedAt(DateTime createdAt) {

		this.createdAt = createdAt;
	}

	@JsonProperty("subject")
	public String getSubject() {

		return subject;
	}

	@JsonProperty("subject")
	public void setSubject(String subject) {

		this.subject = subject;
	}

	@JsonProperty("description")
	public String getDescription() {

		return description;
	}

	@JsonProperty("description")
	public void setDescription(String description) {

		this.description = description;
	}

	@JsonIgnore()
	public int getId() {

		return id;
	}

	@JsonProperty("id")
	public void setId(int id) {

		this.id = id;
	}

	@JsonProperty("priority")
	@JsonSerialize(using = TicketPriority.Serializer.class, include = JsonSerialize.Inclusion.NON_NULL)
	public TicketPriority getPriority() {

		return priority;
	}

	@JsonProperty("priority")
	@JsonDeserialize(using = TicketPriority.Deserializer.class)
	public void setPriority(TicketPriority priority) {

		this.priority = priority;
	}

	@JsonProperty("status")
	@JsonSerialize(using = TicketStatus.Serializer.class, include = JsonSerialize.Inclusion.NON_NULL)
	public TicketStatus getStatus() {

		return status;
	}

	@JsonProperty("status")
	@JsonDeserialize(using = TicketStatus.Deserializer.class)
	public void setStatus(TicketStatus status) {

		this.status = status;
	}

	@JsonProperty("requester_id")
	public int getRequesterId() {

		return requesterId;
	}

	@JsonProperty("requester_id")
	public void setRequesterId(int requesterId) {

		this.requesterId = requesterId;
	}

	@JsonProperty("type")
	@JsonSerialize(using = TicketType.Serializer.class, include = JsonSerialize.Inclusion.NON_NULL)
	public TicketType getType() {

		return type;
	}

	@JsonProperty("type")
	@JsonDeserialize(using = TicketType.Deserializer.class)
	public void setType(TicketType type) {

		this.type = type;
	}

	public DateTime getUpdatedAt() {

		return updatedAt;
	}

	@JsonProperty("updated_at")
	@JsonDeserialize(using = JsonDateTimeDeserializer.class)
	public void setUpdatedAt(DateTime updatedAt) {

		this.updatedAt = updatedAt;
	}

	@JsonProperty("comments")
	public List<TicketComment> getComments() {

		return comments;
	}

	@JsonProperty("comments")
	public void setComments(List<TicketComment> comments) {

		this.comments = comments;
	}

	@JsonProperty("comment")
	public TicketComment getComment() {

		return comment;
	}

	@JsonProperty("comment")
	public void setComment(TicketComment comment) {

		this.comment = comment;
	}

	@JsonProperty("fields")
	public List<TicketFieldEntry> getEntries() {

		return entries;
	}

	@JsonProperty("fields")
	public void setEntries(List<TicketFieldEntry> entries) {

		this.entries = entries;
	}

	@JsonIgnore()
	public String getTicketField(int fieldId) {

		for (TicketFieldEntry field : this.getEntries()) {
			if (field.getFieldId() == fieldId) { return field.getValue(); }
		}

		return null;
	}

	@JsonIgnore()
	public void mapCustomField(CustomField field, int fieldId) {

		customFields.put(field, this.getTicketField(fieldId));
	}

	@JsonIgnore()
	public String getCustomFieldValue(CustomField field) {

		return customFields.get(field);
	}
}
