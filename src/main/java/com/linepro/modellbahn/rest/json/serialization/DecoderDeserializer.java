package com.linepro.modellbahn.rest.json.serialization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.persistence.repository.IDecoderRepository;
import com.linepro.modellbahn.rest.util.ApiNames;

@JsonComponent
public class DecoderDeserializer extends AbstractItemDeserializer<IDecoder> {

	private IDecoderRepository persister;

	@Autowired
    protected DecoderDeserializer(IDecoderRepository persister) {
        this.persister = persister;
    }

	@Override
	IDecoder findItem(ObjectNode node) throws Exception {
		return persister.findByDecoderId(node.get(ApiNames.DECODER_ID).asText());
	}
}
