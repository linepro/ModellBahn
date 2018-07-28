package com.linepro.modellbahn.rest.json.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.linepro.modellbahn.model.impl.DecoderCV;
import com.linepro.modellbahn.rest.json.LinkUtils;
import com.linepro.modellbahn.rest.util.ApiNames;

public class DecoderCVSerializer extends StdSerializer<DecoderCV> {

    private static final long serialVersionUID = 3171815401312192228L;

    protected LinkUtils utils = new LinkUtils();

    public DecoderCVSerializer() {
        this(DecoderCV.class);
    }

    public DecoderCVSerializer(Class<DecoderCV> t) {
        super(t);
    }

    @Override
    public void serialize(DecoderCV value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeObjectField(ApiNames.CV, value.getCV().getCV());
        gen.writeObjectField(ApiNames.WERT, value.getWert());
        gen.writeEndObject();
    }
}
