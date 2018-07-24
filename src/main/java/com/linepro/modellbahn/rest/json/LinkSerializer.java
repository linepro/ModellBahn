package com.linepro.modellbahn.rest.json;

import java.io.IOException;

import javax.ws.rs.core.Link;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * LinkSerializer.
 * Custom serializer for Link to ensure only the HATEOAS fields are provided 
 * 
 * @author   $Author$
 * @version  $Id$
 */
public class LinkSerializer extends JsonSerializer<Link> {

    @Override
    public void serialize(Link link, JsonGenerator jg, SerializerProvider sp)
            throws IOException, JsonProcessingException {
        jg.writeStartObject();
        jg.writeStringField("href", link.getUri().toString());
        jg.writeStringField("rel", link.getRel());
        jg.writeStringField("method", link.getType());
        jg.writeEndObject();
    }
}