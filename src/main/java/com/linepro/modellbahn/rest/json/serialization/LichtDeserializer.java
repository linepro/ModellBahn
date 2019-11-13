package com.linepro.modellbahn.rest.json.serialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.linepro.modellbahn.model.ILicht;
import com.linepro.modellbahn.persistence.repository.ILandRepository;

@JsonComponent
public class LichtDeserializer extends AbstractNamedItemDeserializer<ILicht> {

	@Autowired
    protected LichtDeserializer(ILandRepository persister) {
        super(persister);
    }
}
