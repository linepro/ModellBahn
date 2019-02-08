package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.keys.DecoderKey;
import com.linepro.modellbahn.persistence.IKey;
import com.linepro.modellbahn.rest.util.ApiNames;

public class DecoderDeserializer extends AbstractItemDeserializer<IDecoder> {

    private static final long serialVersionUID = -871977401187476757L;

    protected DecoderDeserializer() {
        this(IDecoder.class);
    }

    protected DecoderDeserializer(Class<IDecoder> vc) {
        super(vc, ApiNames.DECODER_ID);
    }

    @Override
    protected IKey getKey(ObjectNode node) throws Exception {
        String decoderId = node.get(fieldName).asText();
        return new DecoderKey(decoderId);
    }
}
