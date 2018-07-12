package com.linepro.modellbahn.model;

import java.math.BigDecimal;
import java.util.Set;

public interface IDecoderTyp {

    IAdressTyp getTyp();

    void setTyp(IAdressTyp typ);

    IHersteller getHersteller();

    void setHersteller(IHersteller hersteller);

    String getBestellNr();

    void setBestellNr(String bestellNr);

    Integer getAdressen();

    void setAdressen(Integer adressen);

    BigDecimal getiMax();

    void setiMax(BigDecimal iMax);

    IProtokoll getProtokoll();

    void setProtokoll(IProtokoll protokoll);

    Integer getFahrstufe();

    void setFahrstufe(Integer fahrstufe);

    Boolean getSound();

    void setSound(Boolean sound);

    Set<IDecoderTypCV> getCv();

    void setCv(Set<IDecoderTypCV> cv);

    Set<IDecoderTypFunktion> getFunktion();

    void setFunktion(Set<IDecoderTypFunktion> funktion);

}