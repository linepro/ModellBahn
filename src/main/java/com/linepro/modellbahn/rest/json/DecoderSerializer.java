package com.linepro.modellbahn.rest.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.linepro.modellbahn.model.impl.Decoder;
import com.linepro.modellbahn.rest.util.ApiNames;

/**
 * LinkSerializer.
 * Custom serializer for Link to ensure only the HATEOAS fields are provided 
 * 
 * @author   $Author$
 * @version  $Id$
 */
public class DecoderSerializer extends StdSerializer<Decoder> {

    private static final long serialVersionUID = -4711405906405775674L;

    protected LinkUtils utils = new LinkUtils();

    public DecoderSerializer() {
        this(Decoder.class);
    }

    public DecoderSerializer(Class<Decoder> t) {
        super(t);
    }

    @Override
    public void serialize(Decoder value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
        gen.writeStartObject();
        gen.writeStringField(ApiNames.DECODER, value.getName());
        gen.writeStringField(ApiNames.HERSTELLER, value.getDecoderTyp().getHersteller().getName());
        gen.writeStringField(ApiNames.BESTELL_NR, value.getDecoderTyp().getName());
        utils.writeLinks(ApiNames.LINKS, value.getLinks(), gen, serializers);
        gen.writeEndObject();
    }
}