package com.linepro.modellbahn.rest.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.linepro.modellbahn.model.impl.Produkt;
import com.linepro.modellbahn.rest.util.ApiNames;

/**
 * LinkSerializer.
 * Custom serializer for Link to ensure only the HATEOAS fields are provided 
 * 
 * @author   $Author$
 * @version  $Id$
 */
public class ProduktSerializer extends StdSerializer<Produkt> {

    private static final long serialVersionUID = -3541810693407134622L;

    protected LinkUtils utils = new LinkUtils();

    public ProduktSerializer() {
        this(Produkt.class);
    }

    public ProduktSerializer(Class<Produkt> t) {
        super(t);
    }

    @Override
    public void serialize(Produkt value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeStringField(ApiNames.HERSTELLER, value.getHersteller().getName());
        gen.writeStringField(ApiNames.BESTELL_NR, value.getBestellNr());
        utils.writeLinks(ApiNames.LINKS, value.getLinks(), gen, serializers);
        gen.writeEndObject();
    }
}