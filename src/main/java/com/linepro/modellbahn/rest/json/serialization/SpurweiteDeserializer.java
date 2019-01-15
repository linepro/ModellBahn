package com.linepro.modellbahn.rest.json.serialization;

import com.linepro.modellbahn.model.ISpurweite;

public class SpurweiteDeserializer extends AbstractItemDeserializer<ISpurweite> {

    private static final long serialVersionUID = -871977401187476757L;

    protected SpurweiteDeserializer() {
        this(ISpurweite.class);
    }

    protected SpurweiteDeserializer(Class<ISpurweite> vc) {
        super(vc);
     }
}
