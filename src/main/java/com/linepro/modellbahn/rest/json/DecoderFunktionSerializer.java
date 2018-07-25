package com.linepro.modellbahn.rest.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.linepro.modellbahn.model.impl.DecoderFunktion;

public class DecoderFunktionSerializer extends StdSerializer<DecoderFunktion> {

    private static final long serialVersionUID = -4964757602475225964L;

    public DecoderFunktionSerializer() {
        this(null);
    }

    public DecoderFunktionSerializer(Class<DecoderFunktion> t) {
        super(t);
    }

    @Override
    public void serialize(DecoderFunktion value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeObjectField("bank", value.getFunktion().getReihe());
        gen.writeObjectField("function", value.getFunktion().getName());
        gen.writeObjectField("description", value.getWert());
        gen.writeEndObject();
    }
}
