package com.linepro.modellbahn.rest.json;

import java.io.IOException;
import java.util.Set;

import javax.ws.rs.core.Link;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;

public class LinkUtils {

    public void writeLinks(String fieldName, Set<Link> links, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeArrayFieldStart(fieldName);
        for (Link link : links) {
            writeLink(link, gen, serializers);
        }
        gen.writeEndArray();
    }

    public void writeLink(Link link, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("href", link.getUri().toString());
        gen.writeStringField("rel", link.getRel());
        gen.writeStringField("method", link.getType());
        gen.writeEndObject();
    }
}
