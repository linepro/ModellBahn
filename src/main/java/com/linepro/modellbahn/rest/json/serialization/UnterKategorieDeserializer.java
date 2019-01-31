package com.linepro.modellbahn.rest.json.serialization;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.linepro.modellbahn.rest.util.ApiNames;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
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
        ObjectNode node = codec.readTree(jp);
        JsonNode kategorieJson = node.get(ApiNames.KATEGORIE);
        String kategorieStr = kategorieJson.get(ApiNames.NAMEN).asText();
        String unterKategorieStr = node.get(ApiNames.NAMEN).asText();

         try {
            IKategorie kategorie = kategoriePerisister.findByKey(kategorieStr, false);

            if (kategorie == null) {
                return null;
            }

            return perisister.findByKey(new UnterKategorieKey(kategorie, unterKategorieStr), false);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    } 
}
