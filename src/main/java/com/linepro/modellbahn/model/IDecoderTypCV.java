package com.linepro.modellbahn.model;

import com.linepro.modellbahn.model.keys.DecoderTypCVKey;

/**
 * IDecoderTypCV.
 * @author   $Author$
 * @version  $Id$
 */
public interface IDecoderTypCV extends IItem<DecoderTypCVKey> {

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
     * Gets the cv.
     *
     * @return the cv
     */
    Integer getCV();

    /**
     * Sets the cv.
     *
     * @param cv the new cv
     */
    void setCV(Integer cv);

    /**
     * Gets the bezeichnung.
     *
     * @return the bezeichnung
     */
    String getBezeichnung();

    /**
     * Sets the bezeichnung.
     *
     * @param bezeichnung the new bezeichnung
     */
    void setBezeichnung(String bezeichnung);

    /**
     * Gets the minimal.
     *
     * @return the minimal
     */
    Integer getMinimal();

    /**
     * Sets the minimal.
     *
     * @param minimal the new minimal
     */
    void setMinimal(Integer minimal);

    /**
     * Gets the maximal.
     *
     * @return the maximal
     */
    Integer getMaximal();

    /**
     * Sets the maximal.
     *
     * @param maximal the new maximal
     */
    void setMaximal(Integer maximal);

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