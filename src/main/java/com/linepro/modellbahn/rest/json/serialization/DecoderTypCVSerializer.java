package com.linepro.modellbahn.rest.json.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.linepro.modellbahn.model.impl.DecoderTypCV;
import com.linepro.modellbahn.rest.json.LinkUtils;
import com.linepro.modellbahn.rest.util.ApiNames;

public class DecoderTypCVSerializer extends StdSerializer<DecoderTypCV> {

    private static final long serialVersionUID = -1639693906489730266L;

    protected LinkUtils utils = new LinkUtils();

    public DecoderTypCVSerializer() {
        this(DecoderTypCV.class);
    }

    public DecoderTypCVSerializer(Class<DecoderTypCV> t) {
        super(t);
    }

    @Override
    public void serialize(DecoderTypCV value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeObjectField(ApiNames.CV, value.getCV());
        gen.writeObjectField(ApiNames.DESCRIPTION, value.getBezeichnung());
        gen.writeObjectField("default", value.getWerkseinstellung());
        utils.writeLinks(ApiNames.LINKS, value.getLinks(), gen, serializers);
        gen.writeEndObject();
    }
}
