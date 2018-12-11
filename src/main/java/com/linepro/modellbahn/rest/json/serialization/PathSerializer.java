package com.linepro.modellbahn.rest.json.serialization;

import java.io.IOException;
import java.nio.file.Path;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.linepro.modellbahn.util.StaticContentFinder;

/**
 * LinkSerializer.
 * Custom serializer for Link to ensure only the HATEOAS fields are provided 
 * 
 * @author   $Author$
 * @version  $Id$
 */
public class PathSerializer extends StdSerializer<Path> {

    private static final long serialVersionUID = 5659169256684166251L;

    public PathSerializer() {
        this(Path.class);
    }

    public PathSerializer(Class<Path> t) {
        super(t);
    }

    @Override
    public void serialize(Path value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeString(StaticContentFinder.getStore().urlForPath(value).toString());
    }
}