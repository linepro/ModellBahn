package com.linepro.modellbahn.rest.json.serialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.persistence.repository.IHerstellerRepository;

@JsonComponent
public class HerstellerDeserializer extends AbstractNamedItemDeserializer<IHersteller> {

	@Autowired
    protected HerstellerDeserializer(IHerstellerRepository persister) {
        super(persister);
    }
}
