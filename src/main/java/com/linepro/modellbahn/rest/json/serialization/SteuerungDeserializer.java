package com.linepro.modellbahn.rest.json.serialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.linepro.modellbahn.model.ISteuerung;
import com.linepro.modellbahn.persistence.repository.ISteuerungRepository;

@JsonComponent
public class SteuerungDeserializer extends AbstractNamedItemDeserializer<ISteuerung> {

    @Autowired
    protected SteuerungDeserializer(ISteuerungRepository persister) {
         super(persister);
    }
}
