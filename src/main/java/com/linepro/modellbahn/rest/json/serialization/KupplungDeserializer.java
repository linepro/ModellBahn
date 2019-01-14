package com.linepro.modellbahn.rest.json.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import com.linepro.modellbahn.model.IKupplung;
import com.linepro.modellbahn.model.impl.Kupplung;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;

public class KupplungDeserializer extends StdDeserializer<IKupplung> {

    private static final long serialVersionUID = -871977401187476757L;

    private final IPersister<Kupplung> perisister;

    protected KupplungDeserializer() {
        this(IKupplung.class);
    }

    protected KupplungDeserializer(Class<?> vc) {
        super(vc);
        
        perisister = StaticPersisterFactory.get().createPersister(Kupplung.class);
    }

    @Override
    public IKupplung deserialize(JsonParser jp,  DeserializationContext dc) throws IOException {
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
