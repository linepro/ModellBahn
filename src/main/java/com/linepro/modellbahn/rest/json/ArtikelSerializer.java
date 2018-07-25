package com.linepro.modellbahn.rest.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.linepro.modellbahn.model.impl.Artikel;

public class ArtikelSerializer extends StdSerializer<Artikel> {

    private static final long serialVersionUID = 5732879150462954795L;

    public ArtikelSerializer() {
        this(null);
    }

    public ArtikelSerializer(Class<Artikel> t) {
        super(t);
    }

    @Override
    public void serialize(Artikel value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeObjectField("artikelNr", value.getName());
        gen.writeObjectField("hersteller", value.getProdukt().getHersteller().getName());
        gen.writeObjectField("bestellNr", value.getProdukt().getBestellNr());
        gen.writeEndObject();
    }
}
