package com.linepro.modellbahn.rest.json.serialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.linepro.modellbahn.model.IZug;
import com.linepro.modellbahn.persistence.repository.IZugRepository;

@JsonComponent
public class ZugDeserializer extends AbstractNamedItemDeserializer<IZug> {

    @Autowired
    protected ZugDeserializer(IZugRepository persister) {
        super(persister);
    }
}
