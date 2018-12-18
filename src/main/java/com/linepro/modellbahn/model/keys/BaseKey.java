package com.linepro.modellbahn.model.keys;

import com.linepro.modellbahn.persistence.IKey;

import javax.persistence.Query;

public abstract class BaseKey implements IKey {

    @Override
    public abstract void addCriteria(Query query) throws Exception;
}
