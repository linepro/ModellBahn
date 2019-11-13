package com.linepro.modellbahn.rest.json.serialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.linepro.modellbahn.model.IMassstab;
import com.linepro.modellbahn.persistence.repository.IMassstabRepository;

@JsonComponent
public class MassstabDeserializer extends AbstractNamedItemDeserializer<IMassstab> {

	@Autowired
    protected MassstabDeserializer(IMassstabRepository persister) {
        super(persister);
    }
}
