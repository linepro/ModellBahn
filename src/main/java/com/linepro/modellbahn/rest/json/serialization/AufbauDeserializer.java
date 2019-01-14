package com.linepro.modellbahn.rest.json.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import com.linepro.modellbahn.model.IAufbau;
import com.linepro.modellbahn.model.impl.Aufbau;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;

public class AufbauDeserializer extends StdDeserializer<IAufbau> {

    private static final long serialVersionUID = -871977401187476757L;

    private final IPersister<Aufbau> perisister;

    protected AufbauDeserializer() {
        this(IAufbau.class);
    }

    protected AufbauDeserializer(Class<?> vc) {
        super(vc);
        
        perisister = StaticPersisterFactory.get().createPersister(Aufbau.class);
    }

    @Override
    public IAufbau deserialize(JsonParser jp,  DeserializationContext dc) throws IOException {
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
