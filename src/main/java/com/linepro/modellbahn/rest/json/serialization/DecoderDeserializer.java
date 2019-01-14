package com.linepro.modellbahn.rest.json.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.TextNode;
import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.model.impl.Decoder;
import com.linepro.modellbahn.persistence.IPersister;
import com.linepro.modellbahn.persistence.impl.StaticPersisterFactory;

public class DecoderDeserializer extends StdDeserializer<IDecoder> {

    private static final long serialVersionUID = -871977401187476757L;

    private final IPersister<Decoder> perisister;

    protected DecoderDeserializer() {
        this(IDecoder.class);
    }

    protected DecoderDeserializer(Class<?> vc) {
        super(vc);
        
        perisister = StaticPersisterFactory.get().createPersister(Decoder.class);
    }

    @Override
    public IDecoder deserialize(JsonParser jp,  DeserializationContext dc) throws IOException {
        ObjectCodec codec = jp.getCodec();
        TextNode node = codec.readTree(jp);
        String decoderId = node.textValue();
        try {
            return perisister.findByKey(decoderId, false);
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    } 
}
