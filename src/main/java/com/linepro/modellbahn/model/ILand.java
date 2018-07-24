package com.linepro.modellbahn.model;

/**
 * ILand.
 * @author   $Author$
 * @version  $Id$
 */
public interface ILand extends INamedItem {

    /**
     * Gets the wahrung.
     *
     * @return the wahrung
     */
    IWahrung getWahrung();

    /**
     * Sets the wahrung.
     *
     * @param wharung the new wahrung
     */
    void setWahrung(IWahrung wharung);

}