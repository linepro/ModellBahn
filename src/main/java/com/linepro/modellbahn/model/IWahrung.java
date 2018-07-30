package com.linepro.modellbahn.model;
import com.linepro.modellbahn.model.keys.NameKey;

/**
 * IWahrung.
 * @author   $Author$
 * @version  $Id$
 */
public interface IWahrung extends INamedItem<NameKey> {

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