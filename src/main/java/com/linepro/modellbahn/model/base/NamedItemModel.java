package com.linepro.modellbahn.model.base;

public interface NamedItemModel extends ItemModel {

    String getName();
    void setName(String name);

    String getBezeichnung();
    void setBezeichnung(String bezeichnung);
}
