package com.XYZ.ticketing.data.zendesk;

import com.XYZ.ticketing.data.zendesk.audit.AuditAPI;
import com.XYZ.ticketing.data.zendesk.ticket.TicketAPI;
import com.XYZ.ticketing.data.zendesk.user.UserAPI;
import com.XYZ.ticketing.data.zendesk.attachment.AttachmentAPI;
import com.XYZ.cmdb.common.data.CustomJacksonJsonProvider;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.client.filter.LoggingFilter;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.deser.CustomDeserializerFactory;
import org.codehaus.jackson.map.deser.StdDeserializerProvider;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;

import java.io.IOException;
import java.util.Properties;

public class APIFactory {

    private final WebResource rootResource;

    private int companyFieldId;
    private int dataFieldId;
    private int subtypeFieldId;
    private int categoryFieldId;

    public APIFactory() throws IOException {

        Properties properties = new Properties();
        properties.load(this.getClass().getResourceAsStream("/ticketing.properties"));

        String baseurl = properties.getProperty("baseurl");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        companyFieldId = Integer.parseInt(properties.getProperty("companyFieldId"));
        dataFieldId = Integer.parseInt(properties.getProperty("dataFieldId"));
        subtypeFieldId = Integer.parseInt(properties.getProperty("subtypeFieldId"));
        categoryFieldId = Integer.parseInt(properties.getProperty("categoryFieldId"));

        /* Jersey uses java.util.logging supposedly there's a way to bridge to slf4j, but I don't trust it  */
        boolean debug = Boolean.parseBoolean(properties.getProperty("debug", "false"));

        ClientConfig config = new DefaultClientConfig();
        config.getSingletons().add(getJsonProvider());
        Client client = Client.create(config);
        if (debug) client.addFilter(new LoggingFilter(System.out));

        this.rootResource = client.resource(baseurl);
        this.rootResource.addFilter(new HTTPBasicAuthFilter(username, password));

    }

    private JacksonJsonProvider getJsonProvider() {

        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationConfig.Feature.READ_ENUMS_USING_TO_STRING, true);
        mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);

        mapper.getSerializationConfig().setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);

        CustomSerializerFactory serializerFactory = new CustomSerializerFactory();
        mapper.setSerializerFactory(serializerFactory);

        CustomDeserializerFactory deserializerFactory = new CustomDeserializerFactory();
        mapper.setDeserializerProvider(new StdDeserializerProvider(deserializerFactory));

        return new CustomJacksonJsonProvider(mapper);
    }

    //TODO: cache these locally so we're not creating them every time, we call them a lot.
    public TicketAPI getTicketAPI() {

        return new TicketAPI(rootResource);
    }

    public UserAPI getUserAPI() {

        return new UserAPI(rootResource);
    }

    public AuditAPI getAuditAPI() {

        return new AuditAPI(rootResource);
    }

    public int getCompanyFieldId() {

        return companyFieldId;
    }

    public int getDataFieldId() {

        return dataFieldId;
    }

    public int getSubtypeFieldId() {

        return subtypeFieldId;
    }

    public int getCategoryFieldId() {

        return categoryFieldId;
    }

    public int[] getCategoryFields() {

        int[] fields = {companyFieldId, dataFieldId, subtypeFieldId, categoryFieldId};
        return fields;
    }
    public AttachmentAPI getAttachmentAPI() {

        return new AttachmentAPI(rootResource);
    }

}
