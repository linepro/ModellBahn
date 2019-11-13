package com.linepro.modellbahn.rest.json.serialization;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.linepro.modellbahn.model.IItem;

public abstract class AbstractItemDeserializer<I extends IItem> extends JsonDeserializer<I> {

    protected static final Logger logger = LoggerFactory.getLogger(AbstractItemDeserializer.class);

    @Override
    public I deserialize(JsonParser jp,  DeserializationContext dc) throws IOException {
        try {
            return findItem(jp.getCodec().readTree(jp));
        } catch (Exception e) {
            logger.error("Deserialization error", e);

            throw new IOException(e.getMessage());
        }
    }

    abstract I findItem(ObjectNode node) throws Exception;
}
