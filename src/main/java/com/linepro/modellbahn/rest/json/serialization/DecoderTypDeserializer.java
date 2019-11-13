package com.linepro.modellbahn.rest.json.serialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.persistence.repository.IDecoderTypRepository;
import com.linepro.modellbahn.rest.util.ApiNames;

@JsonComponent
public class DecoderTypDeserializer extends AbstractItemDeserializer<IDecoderTyp> {

	private final IDecoderTypRepository persister;

    @Autowired
    protected DecoderTypDeserializer(IDecoderTypRepository persister) {
        this.persister = persister;
    }

    @Override
    public IDecoderTyp findItem(ObjectNode node) throws Exception {
        String herstellerStr = node.get(ApiNames.HERSTELLER).get(ApiNames.NAMEN).asText();
        String bestellNr = node.get(ApiNames.BESTELL_NR).asText();

        return persister.findByHerstellerAndBestelNr(herstellerStr, bestellNr);
    } 
}
