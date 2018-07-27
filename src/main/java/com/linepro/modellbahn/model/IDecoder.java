package com.linepro.modellbahn.model;

import java.util.List;

/**
 * IDecoder.
 * @author   $Author$
 * @version  $Id$
 */
public interface IDecoder extends INamedItem {

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
    List<IDecoderAdress> getAdressen();

    /**
     * Sets the adressen.
     *
     * @param adressen the new adressen
     */
    void setAdressen(List<IDecoderAdress> adressen);

    void addAdress(IDecoderAdress adress);

    void removeAdress(IDecoderAdress adress);

    /**
     * Gets the cv.
     *
     * @return the cv
     */
    List<IDecoderCV> getCVs();

    /**
     * Sets the cv.
     *
     * @param cv the new cv
     */
    void setCVs(List<IDecoderCV> cv);

    void addCV(IDecoderCV cv);

    void removeCV(IDecoderCV cv);

    /**
     * Gets the funktionen.
     *
     * @return the funktionen
     */
    List<IDecoderFunktion> getFunktionen();

    /**
     * Sets the funktionen.
     *
     * @param funktionen the new funktionen
     */
    void setFunktionen(List<IDecoderFunktion> funktionen);

    void addFunktion(IDecoderFunktion cv);

    void removeFunktion(IDecoderFunktion cv);
}