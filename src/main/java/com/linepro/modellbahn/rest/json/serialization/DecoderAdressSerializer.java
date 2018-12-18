package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.linepro.modellbahn.model.impl.DecoderAdress;
import com.linepro.modellbahn.rest.json.LinkUtils;
import com.linepro.modellbahn.rest.util.ApiNames;

import java.io.IOException;

public class DecoderAdressSerializer extends StdSerializer<DecoderAdress> {

    private static final long serialVersionUID = 5732879150462954795L;

    protected LinkUtils utils = new LinkUtils();

    public DecoderAdressSerializer() {
        this(DecoderAdress.class);
    }

    public DecoderAdressSerializer(Class<DecoderAdress> t) {
        super(t);
    }

    @Override
    public void serialize(DecoderAdress value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField(ApiNames.ADRESS_TYP, value.getAdressTyp());
        gen.writeObjectField(ApiNames.ADRESS, value.getAdress());
        gen.writeEndObject();
    }
}
