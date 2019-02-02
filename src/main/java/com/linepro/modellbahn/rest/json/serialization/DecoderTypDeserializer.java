package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.linepro.modellbahn.rest.util.ApiNames;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.model.keys.DecoderTypKey;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;

public class DecoderTypDeserializer extends StdDeserializer<IDecoderTyp> {

    private static final long serialVersionUID = -871977401187476757L;

    private final IPersister<IHersteller> herstellerPerisister;

    private final IPersister<IDecoderTyp> perisister;

    protected DecoderTypDeserializer() {
        this(IDecoderTyp.class);
    }

    protected DecoderTypDeserializer(Class<?> vc) {
        super(vc);
        
        herstellerPerisister = StaticPersisterFactory.get().createPersister(IHersteller.class);
        perisister = StaticPersisterFactory.get().createPersister(IDecoderTyp.class);
    }

    @Override
    public IDecoderTyp deserialize(JsonParser jp,  DeserializationContext dc) throws IOException {
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
