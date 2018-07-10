/*
 * INamedItem
 * Author:  JohnG
 * Version: $id$
 */
package com.linepro.modellbahn.model;

/**
 * The Interface INamedItem.
 */
public interface INamedItem extends IItem {

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName();

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name);

    /**
     * Gets the bezeichnung.
     *
     * @return the bezeichnung
     */
    public String getBezeichnung();

    /**
     * Sets the bezeichnung.
     *
     * @param bezeichnung the new bezeichnung
     */
    public void setBezeichnung(String bezeichnung);
}