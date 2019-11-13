package com.linepro.modellbahn.rest.json.serialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.linepro.modellbahn.model.IDecoderTypFunktion;
import com.linepro.modellbahn.persistence.repository.IDecoderTypFunktionRepository;
import com.linepro.modellbahn.rest.util.ApiNames;

@JsonComponent
public class DecoderTypFunktionDeserializer extends AbstractItemDeserializer<IDecoderTypFunktion> {

	private final IDecoderTypFunktionRepository persister;

    @Autowired
    protected DecoderTypFunktionDeserializer(IDecoderTypFunktionRepository persister) {
        this.persister = persister;
    }

    @Override
    public IDecoderTypFunktion findItem(ObjectNode node) throws Exception {
        String herstellerStr = node.get(ApiNames.HERSTELLER).get(ApiNames.NAMEN).asText();
        String bestellNr = node.get(ApiNames.BESTELL_NR).asText();
        Integer reihe = node.get(ApiNames.REIHE).asInt();
        String funktion = node.get(ApiNames.FUNKTION).asText();

        return persister.findByTypAndFunktion(herstellerStr, bestellNr, reihe, funktion);
    } 
}
