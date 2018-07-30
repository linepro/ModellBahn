package com.linepro.modellbahn.model;

import com.linepro.modellbahn.model.keys.DecoderCVKey;

/**
 * IDecoderCV.
 * @author   $Author$
 * @version  $Id$
 */
public interface IDecoderCV extends IItem<DecoderCVKey> {

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
     * Gets the cv.
     *
     * @return the cv
     */
    IDecoderTypCV getCV();

    /**
     * Sets the cv.
     *
     * @param cv the new cv
     */
    void setCV(IDecoderTypCV cv);

    Integer getCVValue();

    void setCVValue(Integer cv);

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