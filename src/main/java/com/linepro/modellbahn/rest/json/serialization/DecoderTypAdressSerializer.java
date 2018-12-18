package com.linepro.modellbahn.rest.json.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.linepro.modellbahn.model.impl.DecoderTypAdress;
import com.linepro.modellbahn.rest.json.LinkUtils;
import com.linepro.modellbahn.rest.util.ApiNames;

public class DecoderTypAdressSerializer extends StdSerializer<DecoderTypAdress> {

    private static final long serialVersionUID = -1639693906489730266L;

    private final LinkUtils utils = new LinkUtils();

    public DecoderTypAdressSerializer() {
        this(DecoderTypAdress.class);
    }

    public DecoderTypAdressSerializer(Class<DecoderTypAdress> t) {
        super(t);
    }

    @Override
    public void serialize(DecoderTypAdress value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField(ApiNames.INDEX, value.getIndex());
        gen.writeObjectField(ApiNames.ADRESS_TYP, value.getAdressTyp());
        gen.writeObjectField(ApiNames.SPAN, value.getSpan());
        gen.writeObjectField(ApiNames.WERKSEINSTELLUNG, value.getWerkseinstellung());
        utils.writeLinks(ApiNames.LINKS, value.getLinks(), gen, serializers);
        gen.writeEndObject();
    }
}
