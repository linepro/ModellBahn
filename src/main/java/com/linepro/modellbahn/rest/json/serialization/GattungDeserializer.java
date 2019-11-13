package com.linepro.modellbahn.rest.json.serialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.linepro.modellbahn.model.IGattung;
import com.linepro.modellbahn.persistence.repository.IGattungRepository;

@JsonComponent
public class GattungDeserializer extends AbstractNamedItemDeserializer<IGattung> {

	@Autowired
    protected GattungDeserializer(IGattungRepository persister) {
        super(persister);
    }
}
