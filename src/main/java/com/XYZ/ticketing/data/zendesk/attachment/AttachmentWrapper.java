package com.XYZ.ticketing.data.zendesk.attachment;

import org.codehaus.jackson.annotate.JsonProperty;

public class AttachmentWrapper {

	private Attachment upload;

	@JsonProperty("upload")
	public Attachment getAttachment()
	{
		return upload;
	}
	
	@JsonProperty("upload")
	public void setAttachment(Attachment a)
	{
		this.upload = a;
	}
}
