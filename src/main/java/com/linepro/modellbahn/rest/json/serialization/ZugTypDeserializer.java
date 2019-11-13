package com.linepro.modellbahn.rest.json.serialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.linepro.modellbahn.model.IZugTyp;
import com.linepro.modellbahn.persistence.repository.IZugTypRepository;

@JsonComponent
public class ZugTypDeserializer extends AbstractNamedItemDeserializer<IZugTyp> {

    @Autowired
    protected ZugTypDeserializer(IZugTypRepository persister) {
        super(persister);
    }
}
