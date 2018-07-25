package com.linepro.modellbahn.rest.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.linepro.modellbahn.model.impl.DecoderCV;

public class DecoderCVSerializer extends StdSerializer<DecoderCV> {

    private static final long serialVersionUID = 3171815401312192228L;

    public DecoderCVSerializer() {
        this(null);
    }

    public DecoderCVSerializer(Class<DecoderCV> t) {
        super(t);
    }

    @Override
    public void serialize(DecoderCV value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeObjectField("cv", value.getCV().getCV());
        gen.writeObjectField("value", value.getWert());
        gen.writeEndObject();
    }
}
