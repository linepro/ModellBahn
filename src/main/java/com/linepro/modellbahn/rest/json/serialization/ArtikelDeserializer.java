package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.linepro.modellbahn.model.IArtikel;
import com.linepro.modellbahn.model.keys.ArtikelKey;
import com.linepro.modellbahn.persistence.IKey;
import com.linepro.modellbahn.rest.util.ApiNames;

public class ArtikelDeserializer extends AbstractItemDeserializer<IArtikel> {

    private static final long serialVersionUID = -871977401187476757L;

    protected ArtikelDeserializer() {
        this(IArtikel.class);
    }

    protected ArtikelDeserializer(Class<IArtikel> vc) {
        super(vc, ApiNames.ARTIKEL_ID);
    }

    @Override
    protected IKey getKey(ObjectNode node) throws Exception {
        String artikelId = node.get(fieldName).asText();
        return new ArtikelKey(artikelId);
    }
}
