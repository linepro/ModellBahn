package com.linepro.modellbahn.rest.json;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.core.Link;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IItem;

public class ItemsRepresentation<E extends IItem> implements IRepresentation<List<IRepresentation<?>>> {

    protected final List<IRepresentation<?>> items;

    protected final Set<Link> links = new HashSet<Link>();

    public ItemsRepresentation(List<IRepresentation<?>> items) {
        this.items = items;
    }

    @Override
    @JsonGetter("")
    public List<IRepresentation<?>> getItem() {
        return items;
    }
    
    @Override
    @JsonGetter("links")
    @JsonSerialize(contentUsing=LinkSerializer.class)
    public Set<Link> getLinks() {
        return links;
    }
    
    @Override
    public void addLink(Link link) {
        links.add(link);
    }
}
