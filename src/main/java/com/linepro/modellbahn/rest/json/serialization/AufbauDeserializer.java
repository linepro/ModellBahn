package com.linepro.modellbahn.rest.json.serialization;

import org.springframework.boot.jackson.JsonComponent;

import com.linepro.modellbahn.model.IAufbau;
import com.linepro.modellbahn.persistence.repository.IAufbauRepository;

@JsonComponent
public class AufbauDeserializer extends AbstractNamedItemDeserializer<IAufbau> {

    protected AufbauDeserializer(IAufbauRepository persister) {
    	super(persister);
    }
}
