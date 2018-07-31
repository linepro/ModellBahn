package com.linepro.modellbahn.model;

import com.linepro.modellbahn.model.keys.DecoderAdressKey;
import com.linepro.modellbahn.model.util.AdressTyp;

/**
 * IDecoderAdress.
 * @author   $Author$
 * @version  $Id$
 */
public interface IDecoderAdress extends IItem<DecoderAdressKey> {

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

    Integer getReihe();

    void setReihe(Integer reihe);
    
    /**
     * Gets the typ.
     *
     * @return the typ
     */
    AdressTyp getAdressTyp();

    /**
     * Sets the typ.
     *
     * @param typ the new typ
     */
    void setAdressTyp(AdressTyp typ);

    /**
     * Gets the adress.
     *
     * @return the adress
     */
    Integer getAdress();

    /**
     * Sets the adress.
     *
     * @param poles the new adress
     */
    void setAdress(Integer poles);
}