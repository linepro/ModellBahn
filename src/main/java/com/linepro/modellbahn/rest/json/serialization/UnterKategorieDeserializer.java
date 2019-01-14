package com.linepro.modellbahn.rest.json.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import com.linepro.modellbahn.model.IKategorie;
import com.linepro.modellbahn.model.IUnterKategorie;
import com.linepro.modellbahn.model.keys.UnterKategorieKey;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;

public class UnterKategorieDeserializer extends StdDeserializer<IUnterKategorie> {

    private static final long serialVersionUID = -871977401187476757L;

    private final IPersister<IKategorie> kategoriePerisister;
    private final IPersister<IUnterKategorie> perisister;

    protected UnterKategorieDeserializer() {
        this(IUnterKategorie.class);
    }

    protected UnterKategorieDeserializer(Class<?> vc) {
        super(vc);
        
        kategoriePerisister = StaticPersisterFactory.get().createPersister(IKategorie.class);
        perisister = StaticPersisterFactory.get().createPersister(IUnterKategorie.class);
    }

    @Override
    public IUnterKategorie deserialize(JsonParser jp,  DeserializationContext dc) throws IOException {
        ObjectCodec codec = jp.getCodec();
        TextNode node = codec.readTree(jp);
        String name = node.textValue();
        try {
            String[] parts = name.split("/");
            
            IKategorie kategorie = kategoriePerisister.findByKey(parts[0], false);

            if (kategorie == null) {
                return null;
            }

            return perisister.findByKey(new UnterKategorieKey(kategorie, parts[1]), false);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    } 
}
