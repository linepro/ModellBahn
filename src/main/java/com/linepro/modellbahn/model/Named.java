package com.linepro.modellbahn.model;

public interface Named extends SoftDelete {
    String getName();
    void setName(String name);

    String getBezeichnung();
    void setBezeichnung(String bezeichnung);
}

