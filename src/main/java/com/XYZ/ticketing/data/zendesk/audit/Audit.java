package com.XYZ.ticketing.data.zendesk.audit;

import com.XYZ.ticketing.data.zendesk.serialize.JsonDateTimeDeserializer;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.joda.time.DateTime;

import java.util.List;

public class Audit {

    private DateTime dateCreated;
    private List<AuditEvent> events;
    private int authorId;


    public DateTime getDateCreated() {

        return dateCreated;
    }

    @JsonProperty("created_at")
    @JsonDeserialize(using = JsonDateTimeDeserializer.class)
    public void setDateCreated(DateTime dateCreated) {

        this.dateCreated = dateCreated;
    }

    public List<AuditEvent> getEvents() {

        return events;
    }

    @JsonProperty("events")
    public void setEvents(List<AuditEvent> events) {

        this.events = events;
    }

    public int getAuthorId() {

        return authorId;
    }

    @JsonProperty("author_id")
    public void setAuthorId(int authorId) {

        this.authorId = authorId;
    }
}
