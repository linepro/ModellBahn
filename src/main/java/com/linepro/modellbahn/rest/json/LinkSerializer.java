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

    public LinkSerializer() {
        this(null);
    }

    public LinkSerializer(Class<Link> t) {
        super(t);
    }

    @Override
    public void serialize(Link value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeStringField("href", value.getUri().toString());
        gen.writeStringField("rel", value.getRel());
        gen.writeStringField("method", value.getType());
        gen.writeEndObject();
    }
}