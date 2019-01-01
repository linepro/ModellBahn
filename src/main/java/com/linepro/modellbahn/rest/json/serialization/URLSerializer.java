package com.linepro.modellbahn.rest.json.serialization;

import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * LinkSerializer.
 * Custom serializer for Link to ensure only the HATEOAS fields are provided 
 * 
 * @author   $Author$
 * @version  $Id$
 */
public class URLSerializer extends StdSerializer<URL> {

    private static final long serialVersionUID = 5659169256684166251L;

    public URLSerializer() {
        this(URL.class);
    }

    public URLSerializer(Class<URL> t) {
        super(t);
    }

    @Override
    public void serialize(URL value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(value.toExternalForm());
    }
}