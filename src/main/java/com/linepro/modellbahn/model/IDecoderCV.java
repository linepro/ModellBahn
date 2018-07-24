package com.linepro.modellbahn.model;

/**
 * IDecoderCV.
 * @author   $Author$
 * @version  $Id$
 */
public interface IDecoderCV extends IItem {

    /**
     * Gets the decoder.
     *
     * @return the decoder
     */
    public IDecoder getDecoder();

    /**
     * Sets the decoder.
     *
     * @param decoder the new decoder
     */
    public void setDecoder(IDecoder decoder);

    /**
     * Gets the cv.
     *
     * @return the cv
     */
    public IDecoderTypCV getCV();

    /**
     * Sets the cv.
     *
     * @param cv the new cv
     */
    public void setCV(IDecoderTypCV cv);

    /**
     * Gets the wert.
     *
     * @return the wert
     */
    Integer getWert();

    /**
     * Sets the wert.
     *
     * @param wert the new wert
     */
    void setWert(Integer wert);

    /**
     * Hash code.
     *
     * @return the int
     */
    int hashCode();

}