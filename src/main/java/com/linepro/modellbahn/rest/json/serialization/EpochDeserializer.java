package com.linepro.modellbahn.rest.json.serialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.linepro.modellbahn.model.IEpoch;
import com.linepro.modellbahn.persistence.repository.IEpochRepository;

@JsonComponent
public class EpochDeserializer extends AbstractNamedItemDeserializer<IEpoch> {

	@Autowired
    protected EpochDeserializer(IEpochRepository persister) {
        super(persister);
    }
}
