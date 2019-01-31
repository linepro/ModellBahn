package com.linepro.modellbahn.rest.json.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;
import com.linepro.modellbahn.rest.util.ApiNames;

public class AbstractItemDeserializer<I extends IItem<?>> extends StdDeserializer<I> {

    private static final long serialVersionUID = -871977401187476757L;

    private final IPersister<I> perisister;

    private final String fieldName;

    protected AbstractItemDeserializer(Class<I> vc) {
        this(vc, ApiNames.NAMEN);
    }

    protected AbstractItemDeserializer(Class<I> vc, String fieldName) {
        super(vc);
        
        this.perisister = StaticPersisterFactory.get().createPersister(vc);
        this.fieldName = fieldName;
    }

    @Override
    public I deserialize(JsonParser jp,  DeserializationContext dc) throws IOException {
        ObjectCodec codec = jp.getCodec();
        ObjectNode node = codec.readTree(jp);
        String name = node.get(fieldName).asText();
        try {
            return perisister.findByKey(name, false);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    } 
}
