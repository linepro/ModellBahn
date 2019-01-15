package com.linepro.modellbahn.rest.json.serialization;

import com.linepro.modellbahn.model.IMassstab;

public class MassstabDeserializer extends AbstractItemDeserializer<IMassstab> {

    private static final long serialVersionUID = -871977401187476757L;

    protected MassstabDeserializer() {
        this(IMassstab.class);
    }

    protected MassstabDeserializer(Class<IMassstab> vc) {
        super(vc);
    }
}
