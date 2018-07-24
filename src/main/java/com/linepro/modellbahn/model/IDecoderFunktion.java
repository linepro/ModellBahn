package com.linepro.modellbahn.model;

/**
 * IDecoderFunktion.
 * @author   $Author$
 * @version  $Id$
 */
public interface IDecoderFunktion extends IItem {

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
    String getWert();

    /**
     * Sets the wert.
     *
     * @param wert the new wert
     */
    void setWert(String wert);

}
