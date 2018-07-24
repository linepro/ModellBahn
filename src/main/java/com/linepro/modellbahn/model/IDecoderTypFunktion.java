package com.linepro.modellbahn.model;

/**
 * IDecoderTypFunktion.
 * @author   $Author$
 * @version  $Id$
 */
public interface IDecoderTypFunktion extends INamedItem {

    /**
     * Gets the decoder typ.
     *
     * @return the decoder typ
     */
    IDecoderTyp getDecoderTyp();

    /**
     * Sets the decoder typ.
     *
     * @param decoderTyp the new decoder typ
     */
    void setDecoderTyp(IDecoderTyp decoderTyp);

    /**
     * Gets the reihe.
     *
     * @return the reihe
     */
    Integer getReihe();

    /**
     * Sets the reihe.
     *
     * @param reihe the new reihe
     */
    void setReihe(Integer reihe);

    /**
     * Gets the programmable.
     *
     * @return the programmable
     */
    Boolean getProgrammable();
    
    /**
     * Sets the programmable.
     *
     * @param programmable the new programmable
     */
    void setProgrammable(Boolean programmable);
}