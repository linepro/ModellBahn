package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.linepro.modellbahn.model.impl.Decoder;
import com.linepro.modellbahn.rest.json.LinkUtils;
import com.linepro.modellbahn.rest.util.ApiNames;

import java.io.IOException;

/**
 * LinkSerializer.
 * Custom serializer for Link to ensure only the HATEOAS fields are provided 
 * 
 * @author   $Author$
 * @version  $Id$
 */
public class DecoderSerializer extends StdSerializer<Decoder> {

    private static final long serialVersionUID = -4711405906405775674L;

    private final LinkUtils utils = new LinkUtils();

    public DecoderSerializer() {
        this(Decoder.class);
    }

    private DecoderSerializer(Class<Decoder> t) {
        super(t);
    }

    @Override
    public void serialize(Decoder value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField(ApiNames.DECODER, value.getName());
        gen.writeStringField(ApiNames.HERSTELLER, value.getDecoderTyp().getHersteller().getName());
        gen.writeStringField(ApiNames.BESTELL_NR, value.getDecoderTyp().getName());
        utils.writeLinks(ApiNames.LINKS, value.getLinks(), gen, serializers);
        gen.writeEndObject();
    }
}