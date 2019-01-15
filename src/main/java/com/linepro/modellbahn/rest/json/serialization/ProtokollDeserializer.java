package com.linepro.modellbahn.rest.json.serialization;

import com.linepro.modellbahn.model.IProtokoll;

public class ProtokollDeserializer extends AbstractItemDeserializer<IProtokoll> {

    private static final long serialVersionUID = -871977401187476757L;

    protected ProtokollDeserializer() {
        this(IProtokoll.class);
    }

    protected ProtokollDeserializer(Class<IProtokoll> vc) {
        super(vc);
    }
}
