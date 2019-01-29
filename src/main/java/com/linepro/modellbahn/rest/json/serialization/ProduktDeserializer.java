package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.linepro.modellbahn.model.keys.DecoderTypKey;
import com.linepro.modellbahn.rest.util.ApiNames;
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
import com.linepro.modellbahn.rest.util.ApiPaths;

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
        ObjectNode node = codec.readTree(jp);
        JsonNode herstellerJson = node.get(ApiNames.HERSTELLER);
        String herstellerStr = herstellerJson.get(ApiNames.NAMEN).asText();
        String bestellNr = node.get(ApiNames.BESTELL_NR).asText();

        try {
            IHersteller hersteller = herstellerPerisister.findByKey(herstellerStr, false);

            if (hersteller == null) {
                return null;
            }

            return perisister.findByKey(new DecoderTypKey(hersteller, bestellNr), false);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    } 
}
