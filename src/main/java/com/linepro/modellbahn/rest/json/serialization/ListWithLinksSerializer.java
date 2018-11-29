package com.linepro.modellbahn.rest.json.serialization;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.core.Link;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.linepro.modellbahn.model.IItem;
import com.linepro.modellbahn.rest.json.LinkUtils;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.rest.util.ListWithLinks;

/**
 * LinkSerializer.
 * Custom serializer for Link to ensure only the HATEOAS fields are provided 
 * 
 * @author   $Author$
 * @version  $Id$
 */
@SuppressWarnings("rawtypes")
public class ListWithLinksSerializer extends StdSerializer<ListWithLinks> {

    private static final long serialVersionUID = 5659169256684166251L;

    protected LinkUtils utils = new LinkUtils();
    
    public ListWithLinksSerializer() {
        this(ListWithLinks.class);
    }

    public ListWithLinksSerializer(Class<ListWithLinks> t) {
        super(t);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void serialize(ListWithLinks value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStartArray();
        for (IItem entity : (List<IItem>) value.getEntities()) {
            gen.writeObject(entity);
        }
        gen.writeEndArray();
        gen.writeArrayFieldStart(ApiNames.LINKS);
        for (Link link : (List<Link>) value.getLinks()) {
            utils.writeLink(link, gen, serializers);
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }
}