package com.linepro.modellbahn.model;

import java.math.BigDecimal;
import java.util.Date;

import com.linepro.modellbahn.model.util.Status;

/**
 * IArtikel.
 * @author   $Author$
 * @version  $Id$
 */
public interface IArtikel extends INamedItem {

    /**
     * Gets the produkt.
     *
     * @return the produkt
     */
    IProdukt getProdukt();

    /**
     * Sets the produkt.
     *
     * @param produkt the new produkt
     */
    void setProdukt(IProdukt produkt);

    /**
     * Gets the kaufdatum.
     *
     * @return the kaufdatum
     */
    Date getKaufdatum();

    /**
     * Sets the kaufdatum.
     *
     * @param kaufdatum the new kaufdatum
     */
    void setKaufdatum(Date kaufdatum);

    /**
     * Gets the wahrung.
     *
     * @return the wahrung
     */
    IWahrung getWahrung();

    /**
     * Sets the wahrung.
     *
     * @param wahrung the new wahrung
     */
    void setWahrung(IWahrung wahrung);

    /**
     * Gets the preis.
     *
     * @return the preis
     */
    BigDecimal getPreis();

    /**
     * Sets the preis.
     *
     * @param preis the new preis
     */
    void setPreis(BigDecimal preis);

    /**
     * Gets the stuck.
     *
     * @return the stuck
     */
    Integer getStuck();

    /**
     * Sets the stuck.
     *
     * @param stuck the new stuck
     */
    void setStuck(Integer stuck);

    /**
     * Gets the steuerung.
     *
     * @return the steuerung
     */
    ISteuerung getSteuerung();

    /**
     * Sets the steuerung.
     *
     * @param steuerung the new steuerung
     */
    void setSteuerung(ISteuerung steuerung);

    /**
     * Gets the motor typ.
     *
     * @return the motor typ
     */
    IMotorTyp getMotorTyp();

    /**
     * Sets the motor typ.
     *
     * @param motorTyp the new motor typ
     */
    void setMotorTyp(IMotorTyp motorTyp);

    /**
     * Gets the licht.
     *
     * @return the licht
     */
    ILicht getLicht();

    /**
     * Sets the licht.
     *
     * @param licht the new licht
     */
    void setLicht(ILicht licht);

    /**
     * Gets the kupplung.
     *
     * @return the kupplung
     */
    IKupplung getKupplung();

    /**
     * Sets the kupplung.
     *
     * @param kupplung the new kupplung
     */
    void setKupplung(IKupplung kupplung);

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
     * Gets the anmerkung.
     *
     * @return the anmerkung
     */
    String getAnmerkung();

    /**
     * Sets the anmerkung.
     *
     * @param anmerkung the new anmerkung
     */
    void setAnmerkung(String anmerkung);

    /**
     * Gets the beladung.
     *
     * @return the beladung
     */
    String getBeladung();

    /**
     * Sets the beladung.
     *
     * @param beladung the new beladung
     */
    void setBeladung(String beladung);

    /**
     * Gets the status.
     *
     * @return the status
     */
    Status getStatus();

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    void setStatus(Status status);

}