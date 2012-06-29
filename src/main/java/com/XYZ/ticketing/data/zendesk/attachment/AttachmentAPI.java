package com.XYZ.ticketing.data.zendesk.attachment;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.WebResource;
import com.XYZ.ticketing.data.zendesk.attachment.Attachment; 

public class AttachmentAPI {

	private final WebResource rootResource;

	public AttachmentAPI(WebResource rootResource) {

		super();
		this.rootResource = rootResource;
	}
	
	public AttachmentWrapper addAttachment(Attachment attachment) {
		return rootResource.path("/api/v2/uploads.json").accept(MediaType.APPLICATION_JSON_TYPE).type(MediaType.APPLICATION_JSON_TYPE).post(AttachmentWrapper.class, attachment);

	}
}
