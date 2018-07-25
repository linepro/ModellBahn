package com.linepro.modellbahn.rest.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.linepro.modellbahn.model.impl.DecoderTyp;

/**
 * LinkSerializer.
 * Custom serializer for Link to ensure only the HATEOAS fields are provided 
 * 
 * @author   $Author$
 * @version  $Id$
 */
public class DecoderTypSerializer extends StdSerializer<DecoderTyp> {

    private static final long serialVersionUID = -4711405906405775674L;

    public DecoderTypSerializer() {
        this(null);
    }

    public DecoderTypSerializer(Class<DecoderTyp> t) {
        super(t);
    }

    @Override
    public void serialize(DecoderTyp value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeStringField("hersteller", value.getHersteller().getName());
        gen.writeStringField("bestellNr", value.getName());
        gen.writeEndObject();
    }
}