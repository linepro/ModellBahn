package com.linepro.modellbahn.rest.json.serialization;

import com.linepro.modellbahn.model.IKupplung;

public class KupplungDeserializer extends AbstractItemDeserializer<IKupplung> {

    private static final long serialVersionUID = -871977401187476757L;

    protected KupplungDeserializer() {
        this(IKupplung.class);
    }

    protected KupplungDeserializer(Class<IKupplung> vc) {
        super(vc);
    }
}
