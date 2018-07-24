package com.linepro.modellbahn.model;

import java.util.List;

import com.linepro.modellbahn.model.impl.DecoderAdress;
import com.linepro.modellbahn.model.impl.DecoderCV;
import com.linepro.modellbahn.model.impl.DecoderFunktion;

/**
 * IDecoder.
 * @author   $Author$
 * @version  $Id$
 */
public interface IDecoder extends IItem {

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
    void setDecoderTyp(IDecoderTyp decoderTyp );

    /**
     * Gets the protokoll.
     *
     * @return the protokoll
     */
    IProtokoll getProtokoll();

    /**
     * Sets the protokoll.
     *
     * @param protokoll the new protokoll
     */
    void setProtokoll(IProtokoll protokoll);

    /**
     * Gets the fahrstufe.
     *
     * @return the fahrstufe
     */
    Long getFahrstufe();

    /**
     * Sets the fahrstufe.
     *
     * @param fahrstufe the new fahrstufe
     */
    void setFahrstufe(Long fahrstufe);

    /**
     * Gets the adressen.
     *
     * @return the adressen
     */
    List<DecoderAdress> getAdressen();

    /**
     * Sets the adressen.
     *
     * @param adressen the new adressen
     */
    void setAdressen(List<DecoderAdress> adressen);

    /**
     * Gets the cv.
     *
     * @return the cv
     */
    List<DecoderCV> getCVs();

    /**
     * Sets the cv.
     *
     * @param cv the new cv
     */
    void setCVs(List<DecoderCV> cv);

    /**
     * Gets the funktionen.
     *
     * @return the funktionen
     */
    List<DecoderFunktion> getFunktionen();

    /**
     * Sets the funktionen.
     *
     * @param funktionen the new funktionen
     */
    void setFunktionen(List<DecoderFunktion> funktionen);
}