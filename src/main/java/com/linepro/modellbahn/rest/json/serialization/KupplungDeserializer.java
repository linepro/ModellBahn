package com.linepro.modellbahn.rest.json.serialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.linepro.modellbahn.model.IKupplung;
import com.linepro.modellbahn.persistence.repository.IKupplungRepository;

@JsonComponent
public class KupplungDeserializer extends AbstractNamedItemDeserializer<IKupplung> {

	@Autowired
    protected KupplungDeserializer(IKupplungRepository persister) {
        super(persister);
    }
}
