package com.linepro.modellbahn.model;

import java.util.Set;

import javax.ws.rs.core.Link;
import javax.ws.rs.core.UriInfo;

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
     * Adds the HATEOAS links for this item.
     * Always adds self and parent, optionally adds update and delete
     * @param root the root uri
     * @param update add an update link
     * @param delete add a delete link
     */
    IItem addLinks(UriInfo root, boolean update, boolean delete);

    /**
     * Gets the HATEOAS links to this item for Json serialization.
     * 
     * @return the HATEOAS links
     */
    Set<Link> getLinks();

    String getParentId();

    String getLinkId();
}
