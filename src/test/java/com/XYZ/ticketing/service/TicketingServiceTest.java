package com.XYZ.ticketing.service;

import com.XYZ.ticketing.data.zendesk.audit.Audit;
import com.XYZ.ticketing.data.zendesk.audit.AuditEvent;
import com.XYZ.ticketing.data.zendesk.audit.AuditList;
import com.XYZ.ticketing.data.zendesk.ticket.Ticket;
import com.XYZ.ticketing.data.zendesk.ticket.Ticket.CustomField;
import com.XYZ.ticketing.data.zendesk.ticket.TicketPriority;
import com.XYZ.ticketing.data.zendesk.ticket.TicketType;
import com.XYZ.ticketing.data.zendesk.user.User;
import junit.framework.TestCase;
import org.joda.time.DateTime;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class TicketingServiceTest extends TestCase {

    public void testAddDeleteUser() throws IOException {

        String random = Long.toHexString(Double.doubleToLongBits(Math.random()));
        String name = "Test User " + random;
        String email = "test" + random + "@razorfish.com";

        TicketingService ticketing = new TicketingService();
        assertNotNull(ticketing);

        /* Add the user */
        int newid = ticketing.createUser(name, email);
        assertTrue(newid > 0);

        // TODO: test data we get back from the database

        /* Delete the user */
        boolean result = ticketing.deleteUser(newid);
        assertTrue(result);

    }

    public void testSearchGetUser() throws IOException {

        TicketingService ticketing = new TicketingService();
        assertNotNull(ticketing);

        User result = ticketing.getUserByEmail("asdasd@foobarwwwww.com");
        assertNull(result);

        User result2 = ticketing.getUserByEmail("XYZ.development@razorfish.com");
        assertEquals("XYZ.development@razorfish.com", result2.getEmail());

    }

    public void testCreateTicket() throws IOException {

        Random r = new Random();

        TicketingService ticketing = new TicketingService();
        assertNotNull(ticketing);

        int requesterId = 231889391;
        TicketPriority priority = TicketPriority.values()[r.nextInt(TicketPriority.values().length)];
        TicketType type = TicketType.values()[r.nextInt(TicketType.values().length)];
        String subject = "Test Ticket " + Long.toHexString(Double.doubleToLongBits(Math.random()));
        String description = "This is a test ticket -- Please disregard";
        String company = "Test Company";
        String subtype = "Request type " + Integer.toString(r.nextInt(5));
        String category = "Test Category";
        String metadata = "{\"test\":\"hello world\"}";

        int ticketid = ticketing.createTicket(requesterId, priority, type, subject, description, company, category, subtype, metadata);
        assertTrue(ticketid > 0);

    }

    public void testGetTicket() throws IOException {

        TicketingService ticketing = new TicketingService();
        assertNotNull(ticketing);

        Ticket ticket = ticketing.getTicket(1);
        assertNotNull(ticket);
        assertEquals(1, ticket.getId());

        String data = ticket.getCustomFieldValue(CustomField.DATA);
        assertEquals("{\"test\":\"foobar\"   }", data);

    }

    public void testGetUserTickets() throws IOException {

        TicketingService ticketing = new TicketingService();
        assertNotNull(ticketing);

        List<Ticket> tickets = ticketing.getUserTickets(231889391);
        assertNotNull(tickets);
        assertFalse(tickets.isEmpty());

    }

    public void testCreateTicketComment() throws IOException {

        TicketingService ticketing = new TicketingService();
        assertNotNull(ticketing);
		String token = ticketing.addAttachment("logo3w.png", "http://www.google.com/images/srpr/logo3w.png");
        String[] uploads = new String[] {token};
        boolean result = ticketing.createTicketComment(1, 231889391, "This is a test comment " + Long.toHexString(Double.doubleToLongBits(Math.random())), uploads);
        assertTrue(result);

        //TODO: Get the ticket back and check for the comment we just added
    }

    public void testGetAudit() throws IOException {

        TicketingService ticketing = new TicketingService();
        assertNotNull(ticketing);

        AuditList auditlist = ticketing.getTicketAudit(6);
        assertNotNull(auditlist);
        assertTrue(auditlist.getAudits().size() > 0);

        for (Audit audit : auditlist.getAudits()) {

            DateTime created = audit.getDateCreated();

            /* Note: cache these values in a hashmap, this is REALLY slow for
               tickets with the same person commenting over and over again. */
            User user = ticketing.getUser(audit.getAuthorId());
            assertNotNull(user.getName());
            assertNotNull(user.getEmail());

            for (AuditEvent event : audit.getEvents()) {

                String eventType = event.getType();
                boolean isPrivate = event.isPrivate();

                /* Comments only have the body */
                String body = event.getHtmlBody();

                /* Change events populate these fields */
                String fieldName = event.getFieldName();
                String newValue = event.getValue();
                String oldValue = event.getPreviousValue();
            }

        }

    }
	public void testAddAttachment() throws IOException {

        TicketingService ticketing = new TicketingService();
        assertNotNull(ticketing);
        String filename = "image.png";
        String content_url = "http://www.google.com/images/srpr/logo3w.png";
        String token = ticketing.addAttachment(filename, content_url);
        assertTrue(token.length() > 0);

    }
}
