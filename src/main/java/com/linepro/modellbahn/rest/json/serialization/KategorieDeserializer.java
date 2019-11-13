package com.linepro.modellbahn.rest.json.serialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.linepro.modellbahn.model.IKategorie;
import com.linepro.modellbahn.persistence.repository.IKategorieRepository;

@JsonComponent
public class KategorieDeserializer extends AbstractNamedItemDeserializer<IKategorie> {

	@Autowired
    protected KategorieDeserializer(IKategorieRepository persister) {
        super(persister);
    }
}
