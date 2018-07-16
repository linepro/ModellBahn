package com.linepro.modellbahn.rest.json;

import java.util.Set;

import javax.ws.rs.core.Link;

public interface IRepresentation<E> {

    void addLink(Link link);

    E getItem();

    Set<Link> getLinks();

}
