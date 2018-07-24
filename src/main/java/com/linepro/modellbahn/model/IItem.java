package com.linepro.modellbahn.model;

import java.util.Set;

import javax.ws.rs.core.Link;

/**
 * IItem
 * @author   $Author$
 * @version  $Id$
 *
 * IItem is the root for all data items in this project.
 * 
 * NB. All properties are objects because they may need to be null for template matching.
 */
public interface IItem {

    /**
     * Gets the primary key of this item
     *
     * @return the primary key (unique by class but can be repeated in other classes).
     */
    public Long getId();

    /**
     * Sets the primary key for this item
     *
     * @param id the primary key (unique by class but can be repeated in other classes).
     */
    public void setId(Long id);

    /**
     * Gets the state of this item.
     *
     * @return true if this item is soft deleted otherwise false.
     */
    public Boolean getDeleted();

    /**
     * Sets the state for this item.
     *
     * @param deleted - true if this item is soft deleted otherwise false.
     */
    public void setDeleted(Boolean deleted);

    /**
     * Adds an HATEOAS link to this item.
     * 
     * @param link the HATEOAS link
     */
    void addLink(Link link);

    /**
     * Gets the HATEOAS links to this item for Json serialization.
     * 
     * @return the HATEOAS links
     */
    Set<Link> getLinks();
}