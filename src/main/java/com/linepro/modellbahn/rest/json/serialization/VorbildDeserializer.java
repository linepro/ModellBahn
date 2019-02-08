package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.linepro.modellbahn.rest.util.ApiNames;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.linepro.modellbahn.model.IGattung;
import com.linepro.modellbahn.model.IVorbild;
import com.linepro.modellbahn.model.keys.VorbildKey;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;

public class VorbildDeserializer extends StdDeserializer<IVorbild> {

    private static final long serialVersionUID = -871977401187476757L;

    private final IPersister<IGattung> gattungPerisister;

    private final IPersister<IVorbild> perisister;

    protected VorbildDeserializer() {
        this(IVorbild.class);
    }

    protected VorbildDeserializer(Class<?> vc) {
        super(vc);
        
        gattungPerisister = StaticPersisterFactory.get().createPersister(IGattung.class);
        perisister = StaticPersisterFactory.get().createPersister(IVorbild.class);
    }

    @Override
    public IVorbild deserialize(JsonParser jp,  DeserializationContext dc) throws IOException {
        ObjectCodec codec = jp.getCodec();
        ObjectNode node = codec.readTree(jp);
        JsonNode gattungNode = node.get(ApiNames.GATTUNG);
        String name = gattungNode.get(ApiNames.NAMEN).asText();
        try {
            IGattung gattung = gattungPerisister.findByKey(name, false);
            
            if (gattung == null) {
                return null;
            }

            return perisister.findByKey(new VorbildKey(gattung), false);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    } 
}
