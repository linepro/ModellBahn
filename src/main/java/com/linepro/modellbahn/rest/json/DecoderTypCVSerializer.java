package com.linepro.modellbahn.rest.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.linepro.modellbahn.model.impl.DecoderTypCV;

public class DecoderTypCVSerializer extends StdSerializer<DecoderTypCV> {

    private static final long serialVersionUID = -1639693906489730266L;

    public DecoderTypCVSerializer() {
        this(null);
    }

    public DecoderTypCVSerializer(Class<DecoderTypCV> t) {
        super(t);
    }

    @Override
    public void serialize(DecoderTypCV value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeObjectField("cv", value.getCV());
        gen.writeObjectField("description", value.getBezeichnung());
        gen.writeObjectField("default", value.getWerkseinstellung());
        gen.writeEndObject();
    }
}
