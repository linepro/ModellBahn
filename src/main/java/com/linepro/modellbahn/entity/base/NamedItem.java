package com.linepro.modellbahn.entity.base;

public interface NamedItem extends Item {

    String getName();
    void setName(String name);

    String getBezeichnung();
    void setBezeichnung(String bezeichnung);
}
