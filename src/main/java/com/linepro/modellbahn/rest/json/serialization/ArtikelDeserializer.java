package com.linepro.modellbahn.rest.json.serialization;

import com.linepro.modellbahn.model.IArtikel;
import com.linepro.modellbahn.rest.util.ApiNames;

public class ArtikelDeserializer extends AbstractItemDeserializer<IArtikel> {

    private static final long serialVersionUID = -871977401187476757L;

    protected ArtikelDeserializer() {
        this(IArtikel.class);
    }

    protected ArtikelDeserializer(Class<IArtikel> vc) {
        super(vc, ApiNames.ARTIKEL_ID);
    }
}
