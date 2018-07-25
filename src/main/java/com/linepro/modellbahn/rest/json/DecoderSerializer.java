package com.linepro.modellbahn.rest.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.linepro.modellbahn.model.impl.Decoder;

/**
 * LinkSerializer.
 * Custom serializer for Link to ensure only the HATEOAS fields are provided 
 * 
 * @author   $Author$
 * @version  $Id$
 */
public class DecoderSerializer extends StdSerializer<Decoder> {

    private static final long serialVersionUID = -4711405906405775674L;

    public DecoderSerializer() {
        this(null);
    }

    public DecoderSerializer(Class<Decoder> t) {
        super(t);
    }

    @Override
    public void serialize(Decoder value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeStringField("decoder", value.getName());
        gen.writeStringField("hersteller", value.getDecoderTyp().getHersteller().getName());
        gen.writeStringField("bestellNr", value.getDecoderTyp().getName());
        gen.writeEndObject();
    }
}