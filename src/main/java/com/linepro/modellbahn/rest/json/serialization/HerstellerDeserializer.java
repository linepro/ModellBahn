package com.linepro.modellbahn.rest.json.serialization;

import com.linepro.modellbahn.model.IHersteller;

public class HerstellerDeserializer extends AbstractItemDeserializer<IHersteller> {

    private static final long serialVersionUID = -871977401187476757L;

    protected HerstellerDeserializer() {
        this(IHersteller.class);
    }

    protected HerstellerDeserializer(Class<IHersteller> vc) {
        super(vc);
    }
}
