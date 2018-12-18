package com.linepro.modellbahn.model.keys;

import javax.persistence.Query;

import com.linepro.modellbahn.model.IProdukt;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.util.ToStringBuilder;

public class ProduktTeilKey extends BaseKey {

    private final IProdukt produkt;

    private final IProdukt teil;

    public ProduktTeilKey(final IProdukt produkt, final IProdukt teil) {
        this.produkt = produkt;
        this.teil = teil;
    }

    private IProdukt getProdukt() {
        return produkt;
    }

    private IProdukt getTeil() {
        return teil;
    }

    @Override
    public void addCriteria(Query query) {
        query.setParameter(DBNames.PRODUKT, getProdukt());
        query.setParameter(DBNames.TEIL, getTeil());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(ApiNames.PRODUKT, getProdukt())
                .append(ApiNames.TEIL, getTeil())
                .toString();
    }
}
