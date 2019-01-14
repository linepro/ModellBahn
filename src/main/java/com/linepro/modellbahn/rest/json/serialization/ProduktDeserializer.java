package com.linepro.modellbahn.rest.json.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.model.IProdukt;
import com.linepro.modellbahn.model.keys.ProduktKey;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;

public class ProduktDeserializer extends StdDeserializer<IProdukt> {

    private static final long serialVersionUID = -871977401187476757L;

    private final IPersister<IHersteller> herstellerPerisister;

    private final IPersister<IProdukt> perisister;

    protected ProduktDeserializer() {
        this(IProdukt.class);
    }

    protected ProduktDeserializer(Class<?> vc) {
        super(vc);
        
        herstellerPerisister = StaticPersisterFactory.get().createPersister(IHersteller.class);
        perisister = StaticPersisterFactory.get().createPersister(IProdukt.class);
    }

    @Override
    public IProdukt deserialize(JsonParser jp,  DeserializationContext dc) throws IOException {
        ObjectCodec codec = jp.getCodec();
        TextNode node = codec.readTree(jp);
        String name = node.textValue();
        try {
            String[] parts = name.split("/");
            
            IHersteller hersteller = herstellerPerisister.findByKey(parts[0], false);
            
            if (hersteller == null) {
                return null;
            }

            return perisister.findByKey(new ProduktKey(hersteller, parts[1]), false);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    } 
}
