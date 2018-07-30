package com.linepro.modellbahn.model.keys;

import javax.persistence.Query;

import com.linepro.modellbahn.model.IProdukt;
import com.linepro.modellbahn.persistence.DBNames;

public class ProduktTeilKey extends BaseKey {

    protected final IProdukt produkt;

    protected final IProdukt teil;
    
    public ProduktTeilKey(final IProdukt produkt, final IProdukt teil) {
        this.produkt = produkt;
        this.teil = teil;
    }

    public IProdukt getProdukt() {
        return produkt;
    }

    public IProdukt getTeil() {
        return teil;
    }

    @Override
    public void addCriteria(Query query) {
        query.setParameter(DBNames.PRODUKT, getProdukt());
        query.setParameter(DBNames.TEIL, getTeil());
    }
}
