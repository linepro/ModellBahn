package com.linepro.modellbahn.rest.json.serialization;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.model.keys.NameKey;
import com.linepro.modellbahn.persistence.IKey;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;
import com.linepro.modellbahn.rest.util.ApiNames;

public class AbstractItemDeserializer<I extends IItem<?>> extends StdDeserializer<I> {

    private static final long serialVersionUID = -871977401187476757L;

    protected final IPersister<I> perisister;

    protected final String fieldName;

    protected Logger logger;

    protected AbstractItemDeserializer(Class<I> vc) {
        this(vc, ApiNames.NAMEN);
    }

    protected AbstractItemDeserializer(Class<I> vc, String fieldName) {
        super(vc);
        
        this.perisister = StaticPersisterFactory.get().createPersister(vc);
        this.fieldName = fieldName;
        this.logger = LoggerFactory.getILoggerFactory().getLogger(getClass().getName());
    }

    @Override
    public I deserialize(JsonParser jp,  DeserializationContext dc) throws IOException {
        ObjectCodec codec = jp.getCodec();
        ObjectNode node = codec.readTree(jp);

        try {
            IKey name = getKey(node);

            return findItem(name);
        } catch (Exception e) {
            logger.error("Deserialization error", e);

            throw new IOException(e.getMessage());
        }
    }

    protected IKey getKey(ObjectNode node) throws Exception {
        return new NameKey(node.get(fieldName).asText());
    }

    protected I findItem(IKey name) throws Exception {
        return perisister.findByKey(name, false);
    } 
}
