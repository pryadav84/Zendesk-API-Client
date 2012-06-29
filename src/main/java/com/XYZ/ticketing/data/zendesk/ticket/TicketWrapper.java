package com.XYZ.ticketing.data.zendesk.ticket;

import org.codehaus.jackson.annotate.JsonProperty;


public class TicketWrapper {
	
	private Ticket ticket;
	
	@JsonProperty("ticket")
	public Ticket getTicket()
	{
		return ticket;
	}
	
	@JsonProperty("ticket")
	public void setTicket(Ticket ticket)
	{
		this.ticket = ticket;
	}

	
	
}
