package com.linepro.modellbahn.rest.json.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.linepro.modellbahn.model.impl.Artikel;
import com.linepro.modellbahn.model.impl.Produkt;
import com.linepro.modellbahn.rest.json.LinkUtils;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.util.StaticContentFinder;

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
        gen.writeObjectField(ApiNames.BEZEICHNUNG, value.getBezeichnung());
        gen.writeFieldName(ApiNames.PRODUKT);
        new ProduktSerializer().serialize((Produkt) value.getProdukt(), gen, serializers);
        gen.writeObjectField(ApiNames.ABBILDUNG, StaticContentFinder.getStore().urlForPath(value.getAbbildung()));
        utils.writeLinks(ApiNames.LINKS, value.getLinks(), gen, serializers);
        gen.writeEndObject();
    }
}
