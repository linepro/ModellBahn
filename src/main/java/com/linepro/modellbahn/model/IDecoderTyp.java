package com.linepro.modellbahn.model;

import java.math.BigDecimal;
import java.util.Set;

import com.linepro.modellbahn.model.util.AdressTyp;
import com.linepro.modellbahn.model.util.Konfiguration;

public interface IDecoderTyp {

    AdressTyp getTyp();

    void setTyp(AdressTyp typ);

    IHersteller getHersteller();

    void setHersteller(IHersteller hersteller);

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

    Konfiguration getKonfiguration();

    void setKonfiguration(Konfiguration konfiguration);

}