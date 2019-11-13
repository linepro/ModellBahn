package com.linepro.modellbahn.rest.json.serialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.linepro.modellbahn.model.ISonderModell;
import com.linepro.modellbahn.persistence.repository.ISonderModellRepository;

@JsonComponent
public class SonderModellDeserializer extends AbstractNamedItemDeserializer<ISonderModell> {

	@Autowired
    protected SonderModellDeserializer(ISonderModellRepository persister) {
        super(persister);
    }
}
