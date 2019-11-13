package com.linepro.modellbahn.rest.json.serialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.linepro.modellbahn.model.ISpurweite;
import com.linepro.modellbahn.persistence.repository.ISpurweiteRepository;

@JsonComponent
public class SpurweiteDeserializer extends AbstractNamedItemDeserializer<ISpurweite> {

    @Autowired
    protected SpurweiteDeserializer(ISpurweiteRepository persister) {
        super(persister);
     }
}
