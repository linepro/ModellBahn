package com.linepro.modellbahn.rest.json.serialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.linepro.modellbahn.model.IBahnverwaltung;
import com.linepro.modellbahn.persistence.repository.IBahnverwaltungRepository;

@JsonComponent
public class BahnverwaltungDeserializer extends AbstractNamedItemDeserializer<IBahnverwaltung> {

	@Autowired
    protected BahnverwaltungDeserializer(IBahnverwaltungRepository persister) {
    	super(persister);
    }
}
