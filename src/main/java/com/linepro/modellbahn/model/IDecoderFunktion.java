package com.linepro.modellbahn.model;

import com.linepro.modellbahn.model.keys.DecoderFunktionKey;

/**
 * IDecoderFunktion.
 * @author   $Author$
 * @version  $Id$
 */
public interface IDecoderFunktion extends IItem<DecoderFunktionKey> {

    /**
     * Gets the decoder.
     *
     * @return the decoder
     */
    IDecoder getDecoder();

    /**
     * Sets the decoder.
     *
     * @param decoder the new decoder
     */
    void setDecoder(IDecoder decoder);

    String getFunktionStr();

    void setFunktionStr(String funktion);

    /**
     * Gets the funktion.
     *
     * @return the funktion
     */
    IDecoderTypFunktion getFunktion();

    /**
     * Sets the funktion.
     *
     * @param funktion the new funktion
     */
    void setFunktion(IDecoderTypFunktion funktion);

    /**
     * Gets the wert.
     *
     * @return the wert
     */
    String getBezeichnung();

    /**
     * Sets the wert.
     *
     * @param wert the new wert
     */
    void setBezeichnung(String wert);

}
