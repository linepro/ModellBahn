package com.linepro.modellbahn.rest.json.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.linepro.modellbahn.model.impl.DecoderTyp;
import com.linepro.modellbahn.rest.json.LinkUtils;
import com.linepro.modellbahn.rest.util.ApiNames;

/**
 * LinkSerializer.
 * Custom serializer for Link to ensure only the HATEOAS fields are provided 
 * 
 * @author   $Author$
 * @version  $Id$
 */
public class DecoderTypSerializer extends StdSerializer<DecoderTyp> {

    private static final long serialVersionUID = -4711405906405775674L;

    protected LinkUtils utils = new LinkUtils();

    public DecoderTypSerializer() {
        this(DecoderTyp.class);
    }

    public DecoderTypSerializer(Class<DecoderTyp> t) {
        super(t);
    }

    @Override
    public void serialize(DecoderTyp value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField(ApiNames.HERSTELLER, value.getHersteller().getName());
        gen.writeStringField(ApiNames.BESTELL_NR, value.getName());
        utils.writeLinks(ApiNames.LINKS, value.getLinks(), gen, serializers);
        gen.writeEndObject();
    }
}