package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.linepro.modellbahn.model.IGattung;
import com.linepro.modellbahn.model.IVorbild;
import com.linepro.modellbahn.model.keys.VorbildKey;
import com.linepro.modellbahn.persistence.IKey;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;
import com.linepro.modellbahn.rest.util.ApiNames;

public class VorbildDeserializer extends  AbstractItemDeserializer<IVorbild> {

    private static final long serialVersionUID = -871977401187476757L;

    private final IPersister<IGattung> gattungPerisister;

    protected VorbildDeserializer() {
        this(IVorbild.class);
    }

    protected VorbildDeserializer(Class<IVorbild> vc) {
        super(vc);
        
        gattungPerisister = StaticPersisterFactory.get().createPersister(IGattung.class);
    }

    @Override
    protected IKey getKey(ObjectNode node) throws Exception {
        JsonNode gattungNode = node.get(ApiNames.GATTUNG);
        String name = gattungNode.get(ApiNames.NAMEN).asText();
        IGattung gattung = gattungPerisister.findByKey(name, false);
        return new VorbildKey(gattung);
    }
}
