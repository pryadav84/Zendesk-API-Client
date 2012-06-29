package com.XYZ.ticketing.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.XYZ.ticketing.data.zendesk.APIFactory;
import com.XYZ.ticketing.data.zendesk.audit.AuditList;
import com.XYZ.ticketing.data.zendesk.ticket.Ticket;
import com.XYZ.ticketing.data.zendesk.ticket.TicketCollection;
import com.XYZ.ticketing.data.zendesk.ticket.TicketComment;
import com.XYZ.ticketing.data.zendesk.ticket.TicketFieldEntry;
import com.XYZ.ticketing.data.zendesk.ticket.TicketPriority;
import com.XYZ.ticketing.data.zendesk.ticket.TicketType;
import com.XYZ.ticketing.data.zendesk.ticket.TicketWrapper;
import com.XYZ.ticketing.data.zendesk.user.User;
import com.XYZ.ticketing.data.zendesk.user.UserCollection;
import com.XYZ.ticketing.data.zendesk.user.UserWrapper;
import com.XYZ.ticketing.data.zendesk.attachment.Attachment;
import com.XYZ.ticketing.data.zendesk.attachment.AttachmentWrapper;

public class TicketingService {

	private APIFactory apiFactory;

	public TicketingService() throws IOException {

		apiFactory = new APIFactory();

	}

	public User getUser(int id) {

		UserWrapper result = apiFactory.getUserAPI().getUser(id);

        return result.getUser();
	}

	public boolean deleteUser(int id) {

		UserWrapper result = apiFactory.getUserAPI().deleteUser(id);

		return !result.getUser().getActive();
	}

	public int createUser(String name, String email) {

		User user = new User();
		user.setEmail(email);
		user.setName(name);
		user.setVerified(true);

		UserWrapper userWrapper = new UserWrapper();
		userWrapper.setUser(user);

		UserWrapper result = apiFactory.getUserAPI().addUser(userWrapper);

		if ((result == null) || (result.getUser() == null)) {
			return 0;
		} else {
			return result.getUser().getId();
		}
	}

	public User getUserByEmail(String email) {

		UserCollection result = apiFactory.getUserAPI().queryUser(email);

		if ((result == null) || (result.getCount() < 1)) {
			return null;
		} else {
			return result.getUsers().get(0);
		}
	}

	public int createTicket(int requesterId, TicketPriority priority, TicketType type, String subject, String description, String company, String category, String subtype, String metadata) {

		TicketFieldEntry companyField = new TicketFieldEntry();
		companyField.setFieldId(apiFactory.getCompanyFieldId());
		companyField.setValue(company);

		TicketFieldEntry subtypeField = new TicketFieldEntry();
		subtypeField.setFieldId(apiFactory.getSubtypeFieldId());
		subtypeField.setValue(subtype);

		TicketFieldEntry dataField = new TicketFieldEntry();
		dataField.setFieldId(apiFactory.getDataFieldId());
		dataField.setValue(metadata);

        TicketFieldEntry categoryField = new TicketFieldEntry();
        categoryField.setFieldId(apiFactory.getCategoryFieldId());
        dataField.setValue(category);

		List<TicketFieldEntry> entries = Arrays.asList(companyField, subtypeField, dataField, categoryField);

		Ticket ticket = new Ticket();
		ticket.setRequesterId(requesterId);
		ticket.setPriority(priority);
		ticket.setType(type);
		ticket.setSubject(subject);
		ticket.setDescription(description);
		ticket.setEntries(entries);

		TicketWrapper ticketWrapper = new TicketWrapper();
		ticketWrapper.setTicket(ticket);

		TicketWrapper result = apiFactory.getTicketAPI().createTicket(ticketWrapper);

		return result.getTicket().getId();
	}

	public Ticket getTicket(int id) {

		TicketWrapper result = apiFactory.getTicketAPI().getTicket(id);

		Ticket ticket = result.getTicket();
		this.mapFields(ticket);

		return ticket;
	}

	public List<Ticket> getUserTickets(int userId) {

		TicketCollection result = apiFactory.getTicketAPI().getUserTickets(userId);

		List<Ticket> tickets = result.getTickets();

		for (Ticket ticket : tickets) {
			this.mapFields(ticket);
		}

		return tickets;
	}

	private void mapFields(Ticket ticket) {

		ticket.mapCustomField(Ticket.CustomField.CATEGORY, apiFactory.getCategoryFieldId());
		ticket.mapCustomField(Ticket.CustomField.COMPANY, apiFactory.getCompanyFieldId());
		ticket.mapCustomField(Ticket.CustomField.DATA, apiFactory.getDataFieldId());
		ticket.mapCustomField(Ticket.CustomField.SUBTYPE, apiFactory.getSubtypeFieldId());
	}

	public boolean createTicketComment(int ticketId, int authorId, String comment, String[] uploads) {

		TicketComment ticketComment = new TicketComment();

		ticketComment.setAuthorId(authorId);
		ticketComment.setValue(comment);
		ticketComment.setPublic(true);
		ticketComment.setUploads(uploads);

		Ticket ticket = new Ticket();
		ticket.setId(ticketId);
		ticket.setRequesterId(authorId);
		ticket.setComment(ticketComment);

		TicketWrapper ticketWrapper = new TicketWrapper();
		ticketWrapper.setTicket(ticket);

		TicketWrapper result = apiFactory.getTicketAPI().updateTicket(ticketWrapper);

		return result != null;
	}

    public AuditList getTicketAudit(int ticketId)
    {
        return apiFactory.getAuditAPI().getAudits(ticketId);
    }
    
    public String addAttachment(String filename, String url) {
		Attachment attachment = new Attachment();
		attachment.setFilename(filename);
		attachment.setUrl(url);
//		attachment.setContentType(contentType);
//		attachment.setSize(size);

		AttachmentWrapper result = apiFactory.getAttachmentAPI().addAttachment(attachment);
		if (result == null){
			return "";
		}else{
			return result.getAttachment().getToken();
		}
	}


}
