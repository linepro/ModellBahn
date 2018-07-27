package com.linepro.modellbahn.model;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * IProdukt.
 * 
 * @author $Author$
 * @version $Id$
 */
public interface IProdukt extends IItem {

    /**
     * Gets the epoch.
     *
     * @return the epoch
     */
    IEpoch getEpoch();

    /**
     * Sets the epoch.
     *
     * @param epoch
     *            the new epoch
     */
    void setEpoch(IEpoch epoch);

    /**
     * Gets the gattung.
     *
     * @return the gattung
     */
    IGattung getGattung();

    /**
     * Sets the gattung.
     *
     * @param gattung
     *            the new gattung
     */
    void setGattung(IGattung gattung);

    /**
     * Gets the bahnverwaltung.
     *
     * @return the bahnverwaltung
     */
    IBahnverwaltung getBahnverwaltung();

    /**
     * Sets the bahnverwaltung.
     *
     * @param bahnverwaltung
     *            the new bahnverwaltung
     */
    void setBahnverwaltung(IBahnverwaltung bahnverwaltung);

    /**
     * Gets the achsfolge.
     *
     * @return the achsfolge
     */
    IAchsfolg getAchsfolg();

    /**
     * Sets the achsfolge.
     *
     * @param achsfolge
     *            the new achsfolge
     */
    void setAchsfolg(IAchsfolg achsfolge);

    /**
     * Gets the massstab.
     *
     * @return the massstab
     */
    IMassstab getMassstab();

    /**
     * Sets the massstab.
     *
     * @param massstab
     *            the new massstab
     */
    void setMassstab(IMassstab massstab);

    /**
     * Gets the spurweite.
     *
     * @return the spurweite
     */
    ISpurweite getSpurweite();

    /**
     * Sets the spurweite.
     *
     * @param spurweite
     *            the new spurweite
     */
    void setSpurweite(ISpurweite spurweite);

    /**
     * Gets the unter kategorie.
     *
     * @return the unter kategorie
     */
    IUnterKategorie getUnterKategorie();

    /**
     * Sets the unter kategorie.
     *
     * @param unterKategorie
     *            the new unter kategorie
     */
    void setUnterKategorie(IUnterKategorie unterKategorie);

    /**
     * Gets the sondermodel.
     *
     * @return the sondermodel
     */
    ISonderModell getSondermodel();

    /**
     * Sets the sondermodel.
     *
     * @param sondermodel
     *            the new sondermodel
     */
    void setSondermodel(ISonderModell sondermodel);

    /**
     * Gets the aufbau.
     *
     * @return the aufbau
     */
    IAufbau getAufbau();

    /**
     * Sets the aufbau.
     *
     * @param aufbau
     *            the new aufbau
     */
    void setAufbau(IAufbau aufbau);

    /**
     * Gets the licht.
     *
     * @return the licht
     */
    ILicht getLicht();

    /**
     * Sets the licht.
     *
     * @param licht
     *            the new licht
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
     * @param kupplung
     *            the new kupplung
     */
    void setKupplung(IKupplung kupplung);

    /**
     * Gets the vorbild.
     *
     * @return the vorbild
     */
    IVorbild getVorbild();

    /**
     * Sets the vorbild.
     *
     * @param vorbild
     *            the new vorbild
     */
    void setVorbild(IVorbild vorbild);

    /**
     * Gets the steuerung.
     *
     * @return the steuerung
     */
    ISteuerung getSteuerung();

    /**
     * Sets the steuerung.
     *
     * @param steuerung
     *            the new steuerung
     */
    void setSteuerung(ISteuerung steuerung);

    /**
     * Gets the decoder typ.
     *
     * @return the decoder typ
     */
    IDecoderTyp getDecoderTyp();

    /**
     * Sets the decoder typ.
     *
     * @param decoderTyp
     *            the new decoder typ
     */
    void setDecoderTyp(IDecoderTyp decoderTyp);

    /**
     * Gets the motor typ.
     *
     * @return the motor typ
     */
    IMotorTyp getMotorTyp();

    /**
     * Sets the motor typ.
     *
     * @param motorTyp
     *            the new motor typ
     */
    void setMotorTyp(IMotorTyp motorTyp);

    /**
     * Gets the hersteller.
     *
     * @return the hersteller
     */
    IHersteller getHersteller();

    /**
     * Sets the hersteller.
     *
     * @param hersteller
     *            the new hersteller
     */
    void setHersteller(IHersteller hersteller);

    /**
     * Gets the bestell nr.
     *
     * @return the bestell nr
     */
    String getBestellNr();

    /**
     * Sets the bestell nr.
     *
     * @param bestellNr
     *            the new bestell nr
     */
    void setBestellNr(String bestellNr);

    /**
     * Gets the anmerkung.
     *
     * @return the anmerkung
     */
    String getAnmerkung();

    /**
     * Sets the anmerkung.
     *
     * @param anmerkung
     *            the new anmerkung
     */
    void setAnmerkung(String anmerkung);

    /**
     * Gets the betreibsnummer.
     *
     * @return the betreibsnummer
     */
    String getBetreibsnummer();

    /**
     * Sets the betreibsnummer.
     *
     * @param betreibsnummer
     *            the new betreibsnummer
     */
    void setBetreibsnummer(String betreibsnummer);

    /**
     * Gets the bauzeit.
     *
     * @return the bauzeit
     */
    Date getBauzeit();

    /**
     * Sets the bauzeit.
     *
     * @param bauzeit
     *            the new bauzeit
     */
    void setBauzeit(Date bauzeit);

    /**
     * Gets the anleitungen.
     *
     * @return the anleitungen
     */
    File getAnleitungen();

    /**
     * Sets the anleitungen.
     *
     * @param anleitungen
     *            the new anleitungen
     */
    void setAnleitungen(File anleitungen);

    /**
     * Gets the explosionszeichnung.
     *
     * @return the explosionszeichnung
     */
    File getExplosionszeichnung();

    /**
     * Sets the explosionszeichnung.
     *
     * @param explosionszeichnung
     *            the new explosionszeichnung
     */
    void setExplosionszeichnung(File explosionszeichnung);

    /**
     * Gets the lange.
     *
     * @return the lange
     */
    BigDecimal getLange();

    /**
     * Sets the lange.
     *
     * @param lange
     *            the new lange
     */
    void setLange(BigDecimal lange);

    /**
     * Gets the abbildung.
     *
     * @return the abbildung
     */
    File getAbbildung();

    /**
     * Sets the abbildung.
     *
     * @param abbildung
     *            the new abbildung
     */
    void setAbbildung(File abbildung);

    /**
     * Gets the teilen.
     *
     * @return the teilen
     */
    List<IProduktTeil> getTeilen();

    /**
     * Sets the teilen.
     *
     * @param teilen
     *            the new teilen
     */
    void setTeilen(List<IProduktTeil> teilen);

    void addTeil(IProduktTeil funktion);

    void removeTeil(IProduktTeil funktion);
}