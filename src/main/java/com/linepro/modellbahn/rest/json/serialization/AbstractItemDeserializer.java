package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;
import java.io.IOException;

public class AbstractItemDeserializer<I extends IItem<?>> extends StdDeserializer<I> {

    private static final long serialVersionUID = -871977401187476757L;

    private final IPersister<I> perisister;

    protected AbstractItemDeserializer(Class<I> vc) {
        super(vc);
        
        perisister = StaticPersisterFactory.get().createPersister(vc);
    }

    @Override
    public I deserialize(JsonParser jp,  DeserializationContext dc) throws IOException {
        ObjectCodec codec = jp.getCodec();
        TextNode node = codec.readTree(jp);
        String name = node.textValue();
        try {
            return perisister.findByKey(name, false);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    } 
}
