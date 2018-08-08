package com.linepro.modellbahn.model;

import com.linepro.modellbahn.model.keys.DecoderTypAdressKey;
import com.linepro.modellbahn.model.util.AdressTyp;

/**
 * IDecoderTypAdress.
 * @author   $Author$
 * @version  $Id$
 */
public interface IDecoderTypAdress extends IItem<DecoderTypAdressKey> {

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
     * Gets the index.
     *
     * @return the index
     */
    Integer getIndex();

    /**
     * Sets the index.
     *
     * @param index the new index
     */
    void setIndex(Integer index);

    /**
     * Gets the adressTyp.
     *
     * @return the adressTyp
     */
    AdressTyp getAdressTyp();

    /**
     * Sets the adressTyp.
     *
     * @param adressTyp the new adressTyp
     */
    void setAdressTyp(AdressTyp adressTyp);

    /**
     * Gets the span.
     *
     * @return the span
     */
    Integer getSpan();

    /**
     * Sets the span.
     *
     * @param span the new span
     */
    void setSpan(Integer span);

    /**
     * Gets the werkseinstellung.
     *
     * @return the werkseinstellung
     */
    Integer getWerkseinstellung();

    /**
     * Sets the werkseinstellung.
     *
     * @param werkseinstellung the new werkseinstellung
     */
    void setWerkseinstellung(Integer werkseinstellung);

}