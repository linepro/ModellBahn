/*
 * INamedItem
 * Author:  JohnG
 * Version: $id$
 */
package com.linepro.modellbahn.model;

import com.linepro.modellbahn.persistence.IKey;

/**
 * INamedItem.
 * @author   $Author$
 * @version  $Id$
 */
public interface INamedItem<K extends IKey> extends IItem<K> {

    /**
     * Gets the name.
     *
     * @return the name
     */
    String getName();

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    void setName(String name);

    /**
     * Gets the bezeichnung.
     *
     * @return the bezeichnung
     */
    String getBezeichnung();

    /**
     * Sets the bezeichnung.
     *
     * @param bezeichnung the new bezeichnung
     */
    void setBezeichnung(String bezeichnung);
}