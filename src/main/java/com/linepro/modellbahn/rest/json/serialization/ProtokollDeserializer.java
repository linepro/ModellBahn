package com.linepro.modellbahn.rest.json.serialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.linepro.modellbahn.model.IProtokoll;
import com.linepro.modellbahn.persistence.repository.IProtokollRepository;

@JsonComponent
public class ProtokollDeserializer extends AbstractNamedItemDeserializer<IProtokoll> {

    @Autowired
    protected ProtokollDeserializer(IProtokollRepository persister) {
        super(persister);
    }
}
