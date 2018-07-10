/*
 * IItem
 * Author:  JohnG
 * Version: $id$
 */
package com.linepro.modellbahn.model;

/**
 * The Interface IItem.
 */
public interface IItem {

    /**
     * Gets the id.
     *
     * @return the id
     */
    public Long getId();

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(Long id);

    /**
     * Gets the deleted.
     *
     * @return the deleted
     */
    public Boolean getDeleted();

    /**
     * Sets the deleted.
     *
     * @param deleted the new deleted
     */
    public void setDeleted(Boolean deleted);
}