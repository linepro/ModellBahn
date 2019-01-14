package com.linepro.modellbahn.rest.json.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import com.linepro.modellbahn.model.IArtikel;
import com.linepro.modellbahn.model.impl.Artikel;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;

public class ArtikelDeserializer extends StdDeserializer<IArtikel> {

    private static final long serialVersionUID = -871977401187476757L;

    private final IPersister<Artikel> perisister;

    protected ArtikelDeserializer() {
        this(IArtikel.class);
    }

    protected ArtikelDeserializer(Class<?> vc) {
        super(vc);
        
        perisister = StaticPersisterFactory.get().createPersister(Artikel.class);
    }

    @Override
    public IArtikel deserialize(JsonParser jp,  DeserializationContext dc) throws IOException {
        ObjectCodec codec = jp.getCodec();
        TextNode node = codec.readTree(jp);
        String artikelId = node.textValue();
        try {
            return perisister.findByKey(artikelId, false);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    } 
}
