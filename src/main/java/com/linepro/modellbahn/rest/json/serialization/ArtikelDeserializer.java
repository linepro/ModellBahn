package com.linepro.modellbahn.rest.json.serialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.linepro.modellbahn.model.IArtikel;
import com.linepro.modellbahn.persistence.repository.IArtikelRepository;
import com.linepro.modellbahn.rest.util.ApiNames;

@JsonComponent
public class ArtikelDeserializer extends AbstractItemDeserializer<IArtikel> {

    private IArtikelRepository persister;

    @Autowired
    protected ArtikelDeserializer(IArtikelRepository persister) {
        this.persister = persister;
    }

    @Override
	IArtikel findItem(ObjectNode node) throws Exception {
		return persister.findByArtikelId(node.get(ApiNames.ARTIKEL_ID).asText());
    }
}
