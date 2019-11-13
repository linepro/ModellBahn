package com.linepro.modellbahn.rest.json.serialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.linepro.modellbahn.model.IWahrung;
import com.linepro.modellbahn.persistence.repository.IWahrungRepository;

@JsonComponent
public class WahrungDeserializer extends AbstractNamedItemDeserializer<IWahrung> {

    @Autowired
    protected WahrungDeserializer(IWahrungRepository persister) {
        super(persister);
    }
}
