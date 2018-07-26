package com.linepro.modellbahn.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * IVorbild.
 * @author   $Author$
 * @version  $Id$
 */
public interface IVorbild extends IItem {
    
    /**
     * Gets the gattung.
     *
     * @return the gattung
     */
    IGattung getGattung();

    /**
     * Sets the gattung.
     *
     * @param gattung the new gattung
     */
    void setGattung(IGattung gattung);

    /**
     * Gets the unter kategorie.
     *
     * @return the unter kategorie
     */
    IUnterKategorie getUnterKategorie();

    /**
     * Sets the unter kategorie.
     *
     * @param unterKategorie the new unter kategorie
     */
    void setUnterKategorie(IUnterKategorie unterKategorie);

    /**
     * Gets the hersteller.
     *
     * @return the hersteller
     */
    String getHersteller();

    /**
     * Sets the hersteller.
     *
     * @param hersteller the new hersteller
     */
    void setHersteller(String hersteller);

    /**
     * Gets the bauzeit.
     *
     * @return the bauzeit
     */
    Date getBauzeit();

    /**
     * Sets the bauzeit.
     *
     * @param bauzeit the new bauzeit
     */
    void setBauzeit(Date bauzeit);

    /**
     * Gets the anzahl.
     *
     * @return the anzahl
     */
    Integer getAnzahl();

    /**
     * Sets the anzahl.
     *
     * @param anzahl the new anzahl
     */
    void setAnzahl(Integer anzahl);

    /**
     * Gets the betriebs nummer.
     *
     * @return the betriebs nummer
     */
    String getBetriebsNummer();

    /**
     * Sets the betriebs nummer.
     *
     * @param betriebsNummer the new betriebs nummer
     */
    void setBetriebsNummer(String betriebsNummer);

    /**
     * Gets the antrieb.
     *
     * @return the antrieb
     */
    IAntrieb getAntrieb();

    /**
     * Sets the antrieb.
     *
     * @param antrieb the new antrieb
     */
    void setAntrieb(IAntrieb antrieb);

    /**
     * Gets the achsfolg.
     *
     * @return the achsfolg
     */
    IAchsfolg getAchsfolg();

    /**
     * Sets the achsfolg.
     *
     * @param achsfolge the new achsfolg
     */
    void setAchsfolg(IAchsfolg achsfolge);

    /**
     * Gets the anfahrzugkraft.
     *
     * @return the anfahrzugkraft
     */
    BigDecimal getAnfahrzugkraft();

    /**
     * Sets the anfahrzugkraft.
     *
     * @param anfahrzugkraft the new anfahrzugkraft
     */
    void setAnfahrzugkraft(BigDecimal anfahrzugkraft);

    /**
     * Gets the leistung.
     *
     * @return the leistung
     */
    BigDecimal getLeistung();

    /**
     * Sets the leistung.
     *
     * @param leistung the new leistung
     */
    void setLeistung(BigDecimal leistung);

    /**
     * Gets the dienstgewicht.
     *
     * @return the dienstgewicht
     */
    BigDecimal getDienstgewicht();

    /**
     * Sets the dienstgewicht.
     *
     * @param dienstgewicht the new dienstgewicht
     */
    void setDienstgewicht(BigDecimal dienstgewicht);

    /**
     * Gets the geschwindigkeit.
     *
     * @return the geschwindigkeit
     */
    Long getGeschwindigkeit();

    /**
     * Sets the geschwindigkeit.
     *
     * @param geschwindigkeit the new geschwindigkeit
     */
    void setGeschwindigkeit(Long geschwindigkeit);

    /**
     * Gets the lange.
     *
     * @return the lange
     */
    BigDecimal getLange();

    /**
     * Sets the lange.
     *
     * @param luep the new lange
     */
    void setLange(BigDecimal luep);

    /**
     * Gets the ausserdienst.
     *
     * @return the ausserdienst
     */
    Date getAusserdienst();

    /**
     * Sets the ausserdienst.
     *
     * @param ausserdienst the new ausserdienst
     */
    void setAusserdienst(Date ausserdienst);

    /**
     * Gets the dm treibrad.
     *
     * @return the dm treibrad
     */
    BigDecimal getDmTreibrad();

    /**
     * Sets the dm treibrad.
     *
     * @param dmTreibrad the new dm treibrad
     */
    void setDmTreibrad(BigDecimal dmTreibrad);

    /**
     * Gets the dm laufrad vorn.
     *
     * @return the dm laufrad vorn
     */
    BigDecimal getDmLaufradVorn();

    /**
     * Sets the dm laufrad vorn.
     *
     * @param dmLaufradVorn the new dm laufrad vorn
     */
    void setDmLaufradVorn(BigDecimal dmLaufradVorn);

    /**
     * Gets the dm laufrad hinten.
     *
     * @return the dm laufrad hinten
     */
    BigDecimal getDmLaufradHinten();

    /**
     * Sets the dm laufrad hinten.
     *
     * @param dmLaufradHinten the new dm laufrad hinten
     */
    void setDmLaufradHinten(BigDecimal dmLaufradHinten);

    /**
     * Gets the zylinder.
     *
     * @return the zylinder
     */
    Integer getZylinder();

    /**
     * Sets the zylinder.
     *
     * @param zylinder the new zylinder
     */
    void setZylinder(Integer zylinder);

    /**
     * Gets the dm zylinder.
     *
     * @return the dm zylinder
     */
    BigDecimal getDmZylinder();

    /**
     * Sets the dm zylinder.
     *
     * @param dmZylinder the new dm zylinder
     */
    void setDmZylinder(BigDecimal dmZylinder);

    /**
     * Gets the kolbenhub.
     *
     * @return the kolbenhub
     */
    BigDecimal getKolbenhub();

    /**
     * Sets the kolbenhub.
     *
     * @param kolbenhub the new kolbenhub
     */
    void setKolbenhub(BigDecimal kolbenhub);

    /**
     * Gets the kesselueberdruck.
     *
     * @return the kesselueberdruck
     */
    BigDecimal getKesselueberdruck();

    /**
     * Sets the kesselueberdruck.
     *
     * @param kesselueberdruck the new kesselueberdruck
     */
    void setKesselueberdruck(BigDecimal kesselueberdruck);

    /**
     * Gets the rostflaeche.
     *
     * @return the rostflaeche
     */
    BigDecimal getRostflaeche();

    /**
     * Sets the rostflaeche.
     *
     * @param rostflaeche the new rostflaeche
     */
    void setRostflaeche(BigDecimal rostflaeche);

    /**
     * Gets the ueberhitzerflaeche.
     *
     * @return the ueberhitzerflaeche
     */
    BigDecimal getUeberhitzerflaeche();

    /**
     * Sets the ueberhitzerflaeche.
     *
     * @param ueberhitzerflaeche the new ueberhitzerflaeche
     */
    void setUeberhitzerflaeche(BigDecimal ueberhitzerflaeche);

    /**
     * Gets the wasservorrat.
     *
     * @return the wasservorrat
     */
    BigDecimal getWasservorrat();

    /**
     * Sets the wasservorrat.
     *
     * @param wasservorrat the new wasservorrat
     */
    void setWasservorrat(BigDecimal wasservorrat);

    /**
     * Gets the verdampfung.
     *
     * @return the verdampfung
     */
    BigDecimal getVerdampfung();

    /**
     * Sets the verdampfung.
     *
     * @param verdampfung the new verdampfung
     */
    void setVerdampfung(BigDecimal verdampfung);

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
     * Gets the fahrmotoren.
     *
     * @return the fahrmotoren
     */
    Integer getFahrmotoren();

    /**
     * Sets the fahrmotoren.
     *
     * @param fahrmotoren the new fahrmotoren
     */
    void setFahrmotoren(Integer fahrmotoren);

    /**
     * Gets the motorbauart.
     *
     * @return the motorbauart
     */
    String getMotorbauart();

    /**
     * Sets the motorbauart.
     *
     * @param motorbauart the new motorbauart
     */
    void setMotorbauart(String motorbauart);

    /**
     * Gets the leistungsuebertragung.
     *
     * @return the leistungsuebertragung
     */
    BigDecimal getLeistungsuebertragung();

    /**
     * Sets the leistungsuebertragung.
     *
     * @param leistungsuebertragung the new leistungsuebertragung
     */
    void setLeistungsuebertragung(BigDecimal leistungsuebertragung);

    /**
     * Gets the reichweite.
     *
     * @return the reichweite
     */
    BigDecimal getReichweite();

    /**
     * Sets the reichweite.
     *
     * @param reichweite the new reichweite
     */
    void setReichweite(BigDecimal reichweite);

    /**
     * Gets the kapazitaet.
     *
     * @return the kapazitaet
     */
    BigDecimal getKapazitat();

    /**
     * Sets the kapazitaet.
     *
     * @param kapazitaet the new kapazitaet
     */
    void setKapazitat(BigDecimal kapazitaet);

    /**
     * Gets the klasse.
     *
     * @return the klasse
     */
    Integer getKlasse();

    /**
     * Sets the klasse.
     *
     * @param klasse the new klasse
     */
    void setKlasse(Integer klasse);

    /**
     * Gets the sitz platze KL 1.
     *
     * @return the sitz platze KL 1
     */
    Long getSitzPlatzeKL1();

    /**
     * Sets the sitz platze KL 1.
     *
     * @param sitzPlatzeKL1 the new sitz platze KL 1
     */
    void setSitzPlatzeKL1(Long sitzPlatzeKL1);

    /**
     * Gets the sitz platze KL 2.
     *
     * @return the sitz platze KL 2
     */
    Long getSitzPlatzeKL2();

    /**
     * Sets the sitz platze KL 2.
     *
     * @param sitzPlatzeKL2 the new sitz platze KL 2
     */
    void setSitzPlatzeKL2(Long sitzPlatzeKL2);

    /**
     * Gets the sitz platze KL 3.
     *
     * @return the sitz platze KL 3
     */
    Long getSitzPlatzeKL3();

    /**
     * Sets the sitz platze KL 3.
     *
     * @param sitzPlatzeKL3 the new sitz platze KL 3
     */
    void setSitzPlatzeKL3(Long sitzPlatzeKL3);

    /**
     * Gets the sitz platze KL 4.
     *
     * @return the sitz platze KL 4
     */
    Long getSitzPlatzeKL4();

    /**
     * Sets the sitz platze KL 4.
     *
     * @param sitzPlatzeKL4 the new sitz platze KL 4
     */
    void setSitzPlatzeKL4(Long sitzPlatzeKL4);

    /**
     * Gets the aufbauten.
     *
     * @return the aufbauten
     */
    String getAufbau();

    /**
     * Sets the aufbauten.
     *
     * @param aufbauten the new aufbauten
     */
    void setAufbau(String aufbauten);

    /**
     * Gets the triebzug anzeigen.
     *
     * @return the triebzug anzeigen
     */
    Boolean getTriebzugAnzeigen();

    /**
     * Sets the triebzug anzeigen.
     *
     * @param triebzugAnzeigen the new triebzug anzeigen
     */
    void setTriebzugAnzeigen(Boolean triebzugAnzeigen);

    /**
     * Gets the triebkoepfe.
     *
     * @return the triebkoepfe
     */
    Long getTriebkoepfe();

    /**
     * Sets the triebkoepfe.
     *
     * @param triebkoepfe the new triebkoepfe
     */
    void setTriebkoepfe(Long triebkoepfe);

    /**
     * Gets the mittelwagen.
     *
     * @return the mittelwagen
     */
    Long getMittelwagen();

    /**
     * Sets the mittelwagen.
     *
     * @param mittelwagen the new mittelwagen
     */
    void setMittelwagen(Long mittelwagen);

    /**
     * Gets the sitz platze TZKL 1.
     *
     * @return the sitz platze TZKL 1
     */
    Long getSitzPlatzeTZKL1();

    /**
     * Sets the sitz platze TZKL 1.
     *
     * @param sitzPlatzeTZKL1 the new sitz platze TZKL 1
     */
    void setSitzPlatzeTZKL1(Long sitzPlatzeTZKL1);

    /**
     * Gets the sitz platze tz KL 2.
     *
     * @return the sitz platze tz KL 2
     */
    Long getSitzPlatzeTzKL2();

    /**
     * Sets the sitz platze tz KL 2.
     *
     * @param sitzPlatzeTzKL2 the new sitz platze tz KL 2
     */
    void setSitzPlatzeTzKL2(Long sitzPlatzeTzKL2);

    /**
     * Gets the drehgestell bauart.
     *
     * @return the drehgestell bauart
     */
    String getDrehgestellBauart();

    /**
     * Sets the drehgestell bauart.
     *
     * @param drehgestellbauart the new drehgestell bauart
     */
    void setDrehgestellBauart(String drehgestellbauart);
}