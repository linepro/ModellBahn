package com.linepro.modellbahn.rest.json.serialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.linepro.modellbahn.model.IAchsfolg;
import com.linepro.modellbahn.persistence.repository.IAchsfolgRepository;

@JsonComponent
public class AchsfolgDeserializer extends AbstractNamedItemDeserializer<IAchsfolg> {

    @Autowired
    public AchsfolgDeserializer(IAchsfolgRepository persister) {
        super(persister);
    }
}