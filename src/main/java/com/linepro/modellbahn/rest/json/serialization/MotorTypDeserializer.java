package com.linepro.modellbahn.rest.json.serialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.linepro.modellbahn.model.IMotorTyp;
import com.linepro.modellbahn.persistence.repository.IMotorTypRepository;

@JsonComponent
public class MotorTypDeserializer extends AbstractNamedItemDeserializer<IMotorTyp> {

    @Autowired
    protected MotorTypDeserializer(IMotorTypRepository persister) {
        super(persister);
    }
}
