package com.linepro.modellbahn.rest.json.serialization;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;

public class LocalDateDeserializer extends StdDeserializer<LocalDate> {

    private static final long serialVersionUID = -871977401187476757L;

    protected LocalDateDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public LocalDate deserialize(JsonParser jp,  DeserializationContext dc) throws IOException {
        ObjectCodec codec = jp.getCodec();
        TextNode node = codec.readTree(jp);
        String dateString = node.textValue();
        return LocalDate.parse(dateString);
    } 
}
