package com.linepro.modellbahn.model;

import java.awt.Image;
import java.util.Date;
import java.util.List;

public interface IProdukt {

    IEpoch getEpoch();

    void setEpoch(IEpoch epoch);

    IGattung getGattung();

    void setGattung(IGattung gattung);

    IBahnverwaltung getBahnverwaltung();

    void setBahnverwaltung(IBahnverwaltung bahnverwaltung);

    IAchsfolg getAchsfolge();

    void setAchsfolge(IAchsfolg achsfolge);

    IMassstab getMassstab();

    void setMassstab(IMassstab massstab);

    ISpurweite getSpurweite();

    void setSpurweite(ISpurweite spurweite);

    IUnterKategorie getUnterKategorie();

    void setUnterKategorie(IUnterKategorie unterKategorie);

    ISonderModell getSondermodel();

    void setSondermodel(ISonderModell sondermodel);

    IAufbau getAufbau();

    void setAufbau(IAufbau aufbau);

    ILicht getLicht();

    void setLicht(ILicht licht);

    IKupplung getKupplung();

    void setKupplung(IKupplung kupplung);

    IVorbild getVorbild();

    void setVorbild(IVorbild vorbild);

    ISteuerung getSteuerung();

    void setSteuerung(ISteuerung steuerung);

    IDecoderTyp getDecoderTyp();

    void setDecoderTyp(IDecoderTyp decoderTyp);

    IMotorTyp getMotorTyp();

    void setMotorTyp(IMotorTyp motorTyp);

    IHersteller getHersteller();

    void setHersteller(IHersteller hersteller);

    String getBestellNr();

    void setBestellNr(String bestellNr);

    String getAnmerkung();

    void setAnmerkung(String anmerkung);

    String getBetreibsnummer();

    void setBetreibsnummer(String betreibsnummer);

    Date getBauzeit();

    void setBauzeit(Date bauzeit);

    String getAnleitungen();

    void setAnleitungen(String anleitungen);

    String getExplosionszeichnung();

    void setExplosionszeichnung(String explosionszeichnung);

    Double getLange();

    void setLange(Double lange);

    Image getAbbildung();

    void setAbbildung(Image abbildung);

    List<IProduktTeil> getTeilen();

    void setTeilen(List<IProduktTeil> teilen);
}