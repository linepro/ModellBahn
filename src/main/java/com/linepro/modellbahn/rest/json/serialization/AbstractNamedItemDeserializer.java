package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.linepro.modellbahn.model.INamedItem;
import com.linepro.modellbahn.persistence.util.INamedItemRepository;
import com.linepro.modellbahn.rest.util.ApiNames;

public abstract class AbstractNamedItemDeserializer<I extends INamedItem> extends AbstractItemDeserializer<I> {

    protected final INamedItemRepository<?> persister;
    
    protected AbstractNamedItemDeserializer(INamedItemRepository<?> persister) {
        this.persister = persister;
    }

    @SuppressWarnings("unchecked")
	I findItem(ObjectNode node) throws Exception {
        return (I) persister.findByName(node.get(ApiNames.NAMEN).asText());
    }
}
