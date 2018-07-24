package com.linepro.modellbahn.rest.json;

import java.util.Set;

import javax.ws.rs.core.Link;

/**
 * IRepresentation.
 * Container to combine links with response 
 * @author   $Author$
 * @version  $Id$
 */
public interface IRepresentation {

    /**
     * Adds the link.
     *
     * @param link the link
     */
    void addLink(Link link);

    /**
     * Gets the entity in this.
     *
     * @return the item
     */
    Object getEntity();

    /**
     * Gets the links.
     *
     * @return the links
     */
    Set<Link> getLinks();

}
