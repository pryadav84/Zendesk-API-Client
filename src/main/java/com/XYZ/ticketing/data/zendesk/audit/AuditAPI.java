package com.XYZ.ticketing.data.zendesk.audit;

import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

public class AuditAPI {

    private final WebResource rootResource;

    public AuditAPI(WebResource rootResource) {

        super();
        this.rootResource = rootResource;
    }

    public AuditList getAudits(int ticketId) {

        return rootResource.path("/api/v2/tickets/" + ticketId + "/audits.json").accept(MediaType.APPLICATION_JSON_TYPE).get(AuditList.class);
    }
}
