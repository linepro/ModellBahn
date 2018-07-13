package com.linepro.modellbahn.model;

public interface IProduktTeil {

    IProdukt getProdukt();

    void setProdukt(IProdukt produkt);

    Integer getAnzahl();

    void setAnzahl(Integer anzahl);

}