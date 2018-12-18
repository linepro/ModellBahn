package com.linepro.modellbahn.rest.json.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.linepro.modellbahn.model.impl.ProduktTeil;
import com.linepro.modellbahn.rest.json.LinkUtils;
import com.linepro.modellbahn.rest.util.ApiNames;

/**
 * LinkSerializer.
 * Custom serializer for Link to ensure only the HATEOAS fields are provided 
 * 
 * @author   $Author$
 * @version  $Id$
 */
public class ProduktTeilSerializer extends StdSerializer<ProduktTeil> {

    private static final long serialVersionUID = -3541810693407134622L;

    private final LinkUtils utils = new LinkUtils();

    public ProduktTeilSerializer() {
        this(ProduktTeil.class);
    }

    public ProduktTeilSerializer(Class<ProduktTeil> t) {
        super(t);
    }

    @Override
    public void serialize(ProduktTeil value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField(ApiNames.HERSTELLER, value.getTeil().getHersteller().getName());
        gen.writeStringField(ApiNames.BESTELL_NR, value.getTeil().getName());
        gen.writeNumberField(ApiNames.ANZAHL, value.getAnzahl());
        utils.writeLinks(ApiNames.LINKS, value.getLinks(), gen, serializers);
        gen.writeEndObject();
    }
}