package com.linepro.modellbahn.rest.json;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Link;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Representation.
 * A holder to attach links in the response body (instead of the header).
 * @author  $Author:$
 * @version $Id:$
 */
public class Representation implements IRepresentation {

    /** The item. */
    protected final Object item;

    /** The links. */
    protected final Set<Link> links = new HashSet<Link>();

    /**
     * Instantiates a new item representation.
     *
     * @param item the item
     */
    public Representation(Object item) {
        this.item = item;
    }

    @Override
    @JsonGetter
    @JsonUnwrapped
    public Object getEntity() {
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
