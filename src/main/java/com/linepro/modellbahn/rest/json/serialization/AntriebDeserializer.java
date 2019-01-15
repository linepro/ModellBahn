package com.linepro.modellbahn.rest.json.serialization;

import com.linepro.modellbahn.model.IAntrieb;

public class AntriebDeserializer extends AbstractItemDeserializer<IAntrieb> {

    private static final long serialVersionUID = -871977401187476757L;

    protected AntriebDeserializer() {
        this(IAntrieb.class);
    }

    protected AntriebDeserializer(Class<IAntrieb> vc) {
        super(vc);
    }
}
