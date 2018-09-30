package com.linepro.modellbahn.rest.json.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.linepro.modellbahn.model.impl.UnterKategorie;
import com.linepro.modellbahn.rest.json.LinkUtils;
import com.linepro.modellbahn.rest.util.ApiNames;

/**
 * LinkSerializer.
 * Custom serializer for Link to ensure only the HATEOAS fields are provided 
 * 
 * @author   $Author$
 * @version  $Id$
 */
public class UnterKategorieSerializer extends StdSerializer<UnterKategorie> {

    private static final long serialVersionUID = -4711405906405775674L;

    protected LinkUtils utils = new LinkUtils();

    public UnterKategorieSerializer() {
        this(UnterKategorie.class);
    }

    public UnterKategorieSerializer(Class<UnterKategorie> t) {
        super(t);
    }

    @Override
    public void serialize(UnterKategorie value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeStringField(ApiNames.NAME, value.getName());
        gen.writeStringField(ApiNames.DESCRIPTION, value.getBezeichnung());
        utils.writeLinks(ApiNames.LINKS, value.getLinks(), gen, serializers);
        gen.writeEndObject();
    }
}