package com.linepro.modellbahn.model;

public interface IZugConsist {

    IZug getZug();

    void setZug(IZug zug);

    Integer getPosition();

    void setPosition(Integer position);

    IArtikel getArtikel();

    void setArtikel(IArtikel artikel);

}