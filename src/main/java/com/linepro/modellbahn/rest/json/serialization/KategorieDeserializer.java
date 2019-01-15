package com.linepro.modellbahn.rest.json.serialization;

import com.linepro.modellbahn.model.IKategorie;

public class KategorieDeserializer extends AbstractItemDeserializer<IKategorie> {

    private static final long serialVersionUID = -871977401187476757L;

    protected KategorieDeserializer() {
        this(IKategorie.class);
    }

    protected KategorieDeserializer(Class<IKategorie> vc) {
        super(vc);
    }
}
