package com.linepro.modellbahn.model.keys;

import javax.persistence.Query;

import com.linepro.modellbahn.persistence.IKey;

public class BaseKey implements IKey {

    @Override
    public void addCriteria(Query query) throws Exception {
    }
}
