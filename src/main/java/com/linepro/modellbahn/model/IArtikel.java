package com.linepro.modellbahn.model;

import java.math.BigDecimal;
import java.util.Date;

import com.linepro.modellbahn.model.impl.Status;

public interface IArtikel {

    IProdukt getProdukt();

    void setProdukt(IProdukt produkt);

    Date getKaufdatum();

    void setKaufdatum(Date kaufdatum);

    IWahrung getWahrung();

    void setWahrung(IWahrung wahrung);

    BigDecimal getPreis();

    void setPreis(BigDecimal preis);

    Integer getStuck();

    void setStuck(Integer stuck);

    ISteuerung getSteuerung();

    void setSteuerung(ISteuerung steuerung);

    IMotorTyp getMotorTyp();

    void setMotorTyp(IMotorTyp motorTyp);

    ILicht getLicht();

    void setLicht(ILicht licht);

    IKupplung getKupplung();

    void setKupplung(IKupplung kupplung);

    IDecoder getDecoder();

    void setDecoder(IDecoder decoder);

    String getAnmerkung();

    void setAnmerkung(String anmerkung);

    String getBeladung();

    void setBeladung(String beladung);

    Status getStatus();

    void setStatus(Status status);

}