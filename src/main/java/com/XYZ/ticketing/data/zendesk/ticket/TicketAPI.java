package com.XYZ.ticketing.data.zendesk.ticket;

import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.WebResource;

public class TicketAPI {

	private final WebResource rootResource;

	public TicketAPI(WebResource rootResource) {

		super();
		this.rootResource = rootResource;
	}

	public TicketWrapper getTicket(int id) {

		return rootResource.path("/api/v2/tickets/" + id + ".json").accept(MediaType.APPLICATION_JSON_TYPE).get(TicketWrapper.class);
	}

	public TicketWrapper createTicket(TicketWrapper ticketWrapper) {

		return rootResource.path("/api/v2/tickets.json").accept(MediaType.APPLICATION_JSON_TYPE).type(MediaType.APPLICATION_JSON_TYPE).post(TicketWrapper.class, ticketWrapper);
	}

	public TicketCollection getUserTickets(int userId) {

		return rootResource.path("/api/v2/users/" + Integer.toString(userId) + "/tickets/requested.json").accept(MediaType.APPLICATION_JSON_TYPE).get(TicketCollection.class);
	}

	public TicketWrapper updateTicket(TicketWrapper ticketWrapper) {

		//TODO: This only adds a comment as the agent for now. The v2 api doesnt yet support X-On-Behalf-Of

		return rootResource.path("/api/v2/tickets/" + Integer.toString(ticketWrapper.getTicket().getId()) + ".json").accept(MediaType.APPLICATION_JSON_TYPE).type(MediaType.APPLICATION_JSON_TYPE).put(TicketWrapper.class, ticketWrapper);
	}
	
}
