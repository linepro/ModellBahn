package com.linepro.modellbahn.rest.json.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.linepro.modellbahn.model.impl.Artikel;
import com.linepro.modellbahn.rest.json.LinkUtils;
import com.linepro.modellbahn.rest.util.ApiNames;

public class ArtikelSerializer extends StdSerializer<Artikel> {

    private static final long serialVersionUID = 5732879150462954795L;

    protected LinkUtils utils = new LinkUtils();

    public ArtikelSerializer() {
        this(Artikel.class);
    }

    private ArtikelSerializer(Class<Artikel> t) {
        super(t);
    }

    @Override
    public void serialize(Artikel value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField(ApiNames.NAMEN, value.getName());
        gen.writeObjectField(ApiNames.HERSTELLER, value.getProdukt().getHersteller().getName());
        gen.writeObjectField(ApiNames.BESTELL_NR, value.getProdukt().getName());
        gen.writeEndObject();
    }
}
