package com.linepro.modellbahn.rest.json.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.linepro.modellbahn.model.impl.DecoderFunktion;
import com.linepro.modellbahn.rest.json.LinkUtils;
import com.linepro.modellbahn.rest.util.ApiNames;

public class DecoderFunktionSerializer extends StdSerializer<DecoderFunktion> {

    private static final long serialVersionUID = -4964757602475225964L;

    private final LinkUtils utils = new LinkUtils();

    public DecoderFunktionSerializer() {
        this(DecoderFunktion.class);
    }

    public DecoderFunktionSerializer(Class<DecoderFunktion> t) {
        super(t);
    }

    @Override
    public void serialize(DecoderFunktion value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("bank", value.getFunktion().getReihe());
        gen.writeObjectField(ApiNames.FUNKTION, value.getFunktion().getName());
        gen.writeObjectField(ApiNames.BEZEICHNUNG, value.getBezeichnung());
        utils.writeLinks(ApiNames.LINKS, value.getLinks(), gen, serializers);
        gen.writeEndObject();
    }
}
