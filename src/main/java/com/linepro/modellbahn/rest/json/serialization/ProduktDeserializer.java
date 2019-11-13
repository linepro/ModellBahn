package com.linepro.modellbahn.rest.json.serialization;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.linepro.modellbahn.model.IProdukt;
import com.linepro.modellbahn.persistence.repository.IProduktRepository;
import com.linepro.modellbahn.rest.util.ApiNames;

public class ProduktDeserializer extends AbstractItemDeserializer<IProdukt> {

	private final IProduktRepository persister;

    @Autowired
    protected ProduktDeserializer(IProduktRepository persister) {
        this.persister = persister;
    }

    @Override
    public IProdukt findItem(ObjectNode node) throws Exception {
        String herstellerStr = node.get(ApiNames.HERSTELLER).get(ApiNames.NAMEN).asText();
        String bestellNr = node.get(ApiNames.BESTELL_NR).asText();

        return persister.findByHerstellerAndBestellNr(herstellerStr, bestellNr);
    } 
}
