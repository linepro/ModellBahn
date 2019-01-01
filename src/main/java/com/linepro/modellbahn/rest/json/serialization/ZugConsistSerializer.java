package com.linepro.modellbahn.rest.json.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.linepro.modellbahn.model.impl.Artikel;
import com.linepro.modellbahn.model.impl.ZugConsist;
import com.linepro.modellbahn.rest.json.LinkUtils;
import com.linepro.modellbahn.rest.util.ApiNames;

/**
 * LinkSerializer.
 * Custom serializer for Link to ensure only the HATEOAS fields are provided 
 * 
 * @author   $Author$
 * @version  $Id$
 */
public class ZugConsistSerializer extends StdSerializer<ZugConsist> {

    private static final long serialVersionUID = -3541810693407134622L;

    private final LinkUtils utils = new LinkUtils();

    public ZugConsistSerializer() {
        this(ZugConsist.class);
    }

    public ZugConsistSerializer(Class<ZugConsist> t) {
        super(t);
    }

    @Override
    public void serialize(ZugConsist value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField(ApiNames.POSITION, value.getPosition());
        gen.writeFieldName(ApiNames.ARTIKEL);
        new ArtikelSerializer().serialize((Artikel) value.getArtikel(), gen, serializers);
        utils.writeLinks(ApiNames.LINKS, value.getLinks(), gen, serializers);
        gen.writeEndObject();
    }
}