package com.linepro.modellbahn.model;

/**
 * IWahrung.
 * @author   $Author$
 * @version  $Id$
 */
public interface IWahrung extends INamedItem {

    /**
     * Gets the decimals.
     *
     * @return the decimals
     */
    Integer getDecimals();

    /**
     * Sets the decimals.
     *
     * @param decimals the new decimals
     */
    void setDecimals(Integer decimals);

}