package com.linepro.modellbahn.model.keys;

import javax.persistence.Query;

import com.linepro.modellbahn.persistence.DBNames;

public class NameKey extends BaseKey {

    private final String name;

    public NameKey(final String name) {
        this.name = name;
    }

    private String getName() {
        return name;
    }

    @Override
    public void addCriteria(Query query) {
        query.setParameter(DBNames.NAME, getName());
    }
}
