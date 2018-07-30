package com.linepro.modellbahn.model;

import com.linepro.modellbahn.model.keys.ProduktTeilKey;

/**
 * IProduktTeil.
 * @author   $Author$
 * @version  $Id$
 */
public interface IProduktTeil extends IItem<ProduktTeilKey> {

    /**
     * Gets the produkt.
     *
     * @return the produkt
     */
    IProdukt getProdukt();

    /**
     * Sets the produkt.
     *
     * @param produkt the new produkt
     */
    void setProdukt(IProdukt produkt);

    /**
     * Gets the anzahl.
     *
     * @return the anzahl
     */
    Integer getAnzahl();

    /**
     * Sets the anzahl.
     *
     * @param anzahl the new anzahl
     */
    void setAnzahl(Integer anzahl);

    IProdukt getTeil();

    void setTeil(IProdukt teil);

}