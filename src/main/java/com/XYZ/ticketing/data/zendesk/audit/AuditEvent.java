package com.XYZ.ticketing.data.zendesk.audit;

import com.XYZ.ticketing.data.zendesk.attachment.Attachment;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

public class AuditEvent {

    private boolean isPrivate;
    private String htmlBody;
    private String body;
    private String type;
    private List<Attachment> attachments;
    private String value;
    private String previousValue;
    private String fieldName;


    public boolean isPrivate() {

        return isPrivate;
    }

    @JsonProperty("private")
    public void setPrivate(boolean isPrivate) {

        this.isPrivate = isPrivate;
    }


    public String getHtmlBody() {

        return htmlBody;
    }

    @JsonProperty("html_body")
    public void setHtmlBody(String htmlBody) {

        this.htmlBody = htmlBody;
    }

    public String getBody() {

        return body;
    }

    @JsonProperty("body")
    public void setBody(String body) {

        this.body = body;
    }

    public String getType() {

        return type;
    }

    @JsonProperty("type")
    public void setType(String type) {

        this.type = type;
    }

    public List<Attachment> getAttachments() {

        return attachments;
    }

    @JsonProperty("attachments")
    public void setAttachments(List<Attachment> attachments) {

        this.attachments = attachments;
    }

    public String getValue() {

        return value;
    }

    @JsonProperty("value")
    public void setValue(String value) {

        this.value = value;
    }

    public String getPreviousValue() {

        return previousValue;
    }

    @JsonProperty("previous_value")
    public void setPreviousValue(String previousValue) {

        this.previousValue = previousValue;
    }

    public String getFieldName() {

        return fieldName;
    }

    @JsonProperty("field_name")
    public void setFieldName(String fieldName) {

        this.fieldName = fieldName;
    }
}
