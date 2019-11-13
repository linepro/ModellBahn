package com.linepro.modellbahn.rest.json.serialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.linepro.modellbahn.model.IAntrieb;
import com.linepro.modellbahn.persistence.repository.IAntriebRepository;

@JsonComponent
public class AntriebDeserializer extends AbstractNamedItemDeserializer<IAntrieb> {

	@Autowired
    protected AntriebDeserializer(IAntriebRepository persister) {
        super(persister);
    }
}
