package com.linepro.modellbahn.rest.json.serialization;

import java.io.IOException;
import java.net.URL;

import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * LinkSerializer.
 * Custom serializer for Link to ensure only the HATEOAS fields are provided 
 * 
 * @author   $Author$
 * @version  $Id$
 */
@JsonComponent
public class URLSerializer extends JsonSerializer<URL> {

    @Override
    public void serialize(URL value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.toExternalForm());
    }
}