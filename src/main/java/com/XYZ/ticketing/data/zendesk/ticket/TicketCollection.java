package com.XYZ.ticketing.data.zendesk.ticket;

import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;


public class TicketCollection {

	private int count;
	private List<Ticket> tickets;

	public int getCount() {

		return count;
	}

	@JsonProperty("count")
	public void setCount(int count) {

		this.count = count;
	}

	public List<Ticket> getTickets() {

		return tickets;
	}

	@JsonProperty("tickets")
	public void setUsers(List<Ticket> tickets) {

		this.tickets = tickets;
	}

	
	
}
