package com.linepro.modellbahn.rest.json.serialization;

import com.linepro.modellbahn.model.IEpoch;

public class EpochDeserializer extends AbstractItemDeserializer<IEpoch> {

    private static final long serialVersionUID = -871977401187476757L;

    protected EpochDeserializer() {
        this(IEpoch.class);
    }

    protected EpochDeserializer(Class<IEpoch> vc) {
        super(vc);
    }
}
