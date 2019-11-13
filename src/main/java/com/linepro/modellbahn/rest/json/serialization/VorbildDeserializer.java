package com.linepro.modellbahn.rest.json.serialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.linepro.modellbahn.model.IVorbild;
import com.linepro.modellbahn.persistence.repository.IVorbildRepository;
import com.linepro.modellbahn.rest.util.ApiNames;

@JsonComponent
public class VorbildDeserializer extends AbstractItemDeserializer<IVorbild> {

    private final IVorbildRepository persister;

    @Autowired
    protected VorbildDeserializer(IVorbildRepository persister) {
        this.persister = persister;
    }

    @Override
    protected IVorbild findItem(ObjectNode node) throws Exception {
        return persister.findByGattung(node.get(ApiNames.GATTUNG).get(ApiNames.NAMEN).asText());
    }
}
