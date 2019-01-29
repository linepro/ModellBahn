package com.linepro.modellbahn.rest.json.serialization;

import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.rest.util.ApiNames;

public class DecoderDeserializer extends AbstractItemDeserializer<IDecoder> {

    private static final long serialVersionUID = -871977401187476757L;

    protected DecoderDeserializer() {
        this(IDecoder.class);
    }

    protected DecoderDeserializer(Class<IDecoder> vc) {
        super(vc, ApiNames.DECODER_ID);
    }
}
