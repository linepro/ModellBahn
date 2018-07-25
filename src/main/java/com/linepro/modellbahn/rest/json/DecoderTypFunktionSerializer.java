package com.linepro.modellbahn.rest.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.linepro.modellbahn.model.impl.DecoderTypFunktion;

public class DecoderTypFunktionSerializer extends StdSerializer<DecoderTypFunktion> {

    private static final long serialVersionUID = 166146761319602613L;

    public DecoderTypFunktionSerializer() {
        this(null);
    }

    public DecoderTypFunktionSerializer(Class<DecoderTypFunktion> t) {
        super(t);
    }

    @Override
    public void serialize(DecoderTypFunktion value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeObjectField("bank", value.getReihe());
        gen.writeObjectField("function", value.getName());
        gen.writeObjectField("description", value.getBezeichnung());
        gen.writeObjectField("programmable", value.getProgrammable());
        gen.writeEndObject();
    }
}
