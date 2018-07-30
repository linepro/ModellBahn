package com.linepro.modellbahn.model;
import com.linepro.modellbahn.model.keys.NameKey;

/**
 * ILand.
 * @author   $Author$
 * @version  $Id$
 */
public interface ILand extends INamedItem<NameKey> {

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