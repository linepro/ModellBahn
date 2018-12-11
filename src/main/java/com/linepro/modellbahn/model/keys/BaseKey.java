package com.linepro.modellbahn.model.keys;

import javax.persistence.Query;

import com.linepro.modellbahn.persistence.IKey;

public abstract class BaseKey implements IKey {

    @Override
    public abstract void addCriteria(Query query) throws Exception;
}
