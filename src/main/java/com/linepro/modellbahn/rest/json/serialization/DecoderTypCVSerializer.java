package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.linepro.modellbahn.model.impl.DecoderTypCV;
import com.linepro.modellbahn.rest.json.LinkUtils;
import com.linepro.modellbahn.rest.util.ApiNames;

import java.io.IOException;

public class DecoderTypCVSerializer extends StdSerializer<DecoderTypCV> {

    private static final long serialVersionUID = -1639693906489730266L;

    private final LinkUtils utils = new LinkUtils();

    public DecoderTypCVSerializer() {
        this(DecoderTypCV.class);
    }

    public DecoderTypCVSerializer(Class<DecoderTypCV> t) {
        super(t);
    }

    @Override
    public void serialize(DecoderTypCV value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField(ApiNames.CV, value.getCv());
        gen.writeObjectField(ApiNames.BEZEICHNUNG, value.getBezeichnung());
        gen.writeObjectField(ApiNames.WERKSEINSTELLUNG, value.getWerkseinstellung());
        utils.writeLinks(ApiNames.LINKS, value.getLinks(), gen, serializers);
        gen.writeEndObject();
    }
}
