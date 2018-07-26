package com.linepro.modellbahn.rest.json;

import java.io.IOException;

import javax.ws.rs.core.Link;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * LinkSerializer.
 * Custom serializer for Link to ensure only the HATEOAS fields are provided 
 * 
 * @author   $Author$
 * @version  $Id$
 */
public class LinkSerializer extends StdSerializer<Link> {

    private static final long serialVersionUID = 5659169256684166251L;

    protected LinkUtils utils = new LinkUtils();
    
    public LinkSerializer() {
        this(Link.class);
    }

    public LinkSerializer(Class<Link> t) {
        super(t);
    }

    @Override
    public void serialize(Link value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        utils.writeLink(value, gen, serializers);
    }
}