package com.linepro.modellbahn.rest.json;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Link;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.linepro.modellbahn.model.IItem;

public class ItemRepresentation<E extends IItem> implements IRepresentation<E> {

    protected final E item;

    protected final Set<Link> links = new HashSet<Link>();

    public ItemRepresentation(E item) {
        this.item = item;
    }

    @Override
    @JsonUnwrapped
    @JsonGetter
    public E getItem() {
        return item;
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
