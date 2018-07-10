package com.linepro.modellbahn.model;

public interface IUnterKategorie extends INamedItem {

    IKategorie getKategorie();

    void setKategorie(IKategorie kategorie);

}