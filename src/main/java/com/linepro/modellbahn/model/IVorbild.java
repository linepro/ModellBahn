package com.linepro.modellbahn.model;

import java.util.Date;

public interface IVorbild extends IItem {
    IGattung getGattung();

    void setGattung(IGattung gattung);

    IUnterKategorie getUnterKategorie();

    void setUnterKategorie(IUnterKategorie unterKategorie);

    String getHersteller();

    void setHersteller(String hersteller);

    Date getBauzeit();

    void setBauzeit(Date bauzeit);

    Integer getAnzahl();

    void setAnzahl(Integer anzahl);

    String getBetriebsNummer();

    void setBetriebsNummer(String betriebsNummer);

    IAntrieb getAntrieb();

    void setAntrieb(IAntrieb antrieb);

    IAchsfolg getAchsfolg();

    void setAchsfolg(IAchsfolg achsfolge);

    Double getAnfahrzugkraft();

    void setAnfahrzugkraft(Double anfahrzugkraft);

    Double getLeistung();

    void setLeistung(Double leistung);

    Double getDienstgewicht();

    void setDienstgewicht(Double dienstgewicht);

    Long getGeschwindigkeit();

    void setGeschwindigkeit(Long geschwindigkeit);

    Double getLange();

    void setLange(Double luep);

    Date getAusserdienst();

    void setAusserdienst(Date ausserdienst);

    Double getDmTreibrad();

    void setDmTreibrad(Double dmTreibrad);

    Double getDmLaufradVorn();

    void setDmLaufradVorn(Double dmLaufradVorn);

    Double getDmLaufradHinten();

    void setDmLaufradHinten(Double dmLaufradHinten);

    Integer getZylinder();

    void setZylinder(Integer zylinder);

    Double getDmZylinder();

    void setDmZylinder(Double dmZylinder);

    Double getKolbenhub();

    void setKolbenhub(Double kolbenhub);

    Double getKesselueberdruck();

    void setKesselueberdruck(Double kesselueberdruck);

    Double getRostflaeche();

    void setRostflaeche(Double rostflaeche);

    Double getUeberhitzerflaeche();

    void setUeberhitzerflaeche(Double ueberhitzerflaeche);

    Double getWasservorrat();

    void setWasservorrat(Double wasservorrat);

    Double getVerdampfung();

    void setVerdampfung(Double verdampfung);

    ISteuerung getSteuerung();

    void setSteuerung(ISteuerung steuerung);

    Integer getFahrmotoren();

    void setFahrmotoren(Integer fahrmotoren);

    String getMotorbauart();

    void setMotorbauart(String motorbauart);

    Double getLeistungsuebertragung();

    void setLeistungsuebertragung(Double leistungsuebertragung);

    Double getReichweite();

    void setReichweite(Double reichweite);

    Double getKapazitaet();

    void setKapazitaet(Double kapazitaet);

    Integer getKlasse();

    void setKlasse(Integer klasse);

    Long getSitzPlatzeKL1();

    void setSitzPlatzeKL1(Long sitzPlatzeKL1);

    Long getSitzPlatzeKL2();

    void setSitzPlatzeKL2(Long sitzPlatzeKL2);

    Long getSitzPlatzeKL3();

    void setSitzPlatzeKL3(Long sitzPlatzeKL3);

    Long getSitzPlatzeKL4();

    void setSitzPlatzeKL4(Long sitzPlatzeKL4);

    String getAufbauten();

    void setAufbauten(String aufbauten);

    Boolean getTriebzugAnzeigen();

    void setTriebzugAnzeigen(Boolean triebzugAnzeigen);

    Long getTriebkoepfe();

    void setTriebkoepfe(Long triebkoepfe);

    Long getMittelwagen();

    void setMittelwagen(Long mittelwagen);

    Long getSitzPlatzeTZKL1();

    void setSitzPlatzeTZKL1(Long sitzPlatzeTZKL1);

    Long getSitzPlatzeTzKL2();

    void setSitzPlatzeTzKL2(Long sitzPlatzeTzKL2);

    String getDrehgestellBauart();

    void setDrehgestellBauart(String drehgestellbauart);
}