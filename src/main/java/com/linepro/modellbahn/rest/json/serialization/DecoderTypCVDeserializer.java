package com.linepro.modellbahn.rest.json.serialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.linepro.modellbahn.model.IDecoderTypCV;
import com.linepro.modellbahn.persistence.repository.IDecoderTypCVRepository;
import com.linepro.modellbahn.rest.util.ApiNames;

@JsonComponent
public class DecoderTypCVDeserializer extends AbstractItemDeserializer<IDecoderTypCV> {

	private final IDecoderTypCVRepository persister;

    @Autowired
    protected DecoderTypCVDeserializer(IDecoderTypCVRepository persister) {
        this.persister = persister;
    }

    @Override
    public IDecoderTypCV findItem(ObjectNode node) throws Exception {
        String herstellerStr = node.get(ApiNames.HERSTELLER).get(ApiNames.NAMEN).asText();
        String bestellNr = node.get(ApiNames.BESTELL_NR).asText();
        Integer cv = node.get(ApiNames.CV).asInt();

        return persister.findByTypAndCv(herstellerStr, bestellNr, cv);
    } 
}
