package com.linepro.modellbahn.model.keys;

import javax.persistence.Query;

import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.persistence.DBNames;

public class DecoderTypKey extends BaseKey {

    protected final IHersteller hersteller;
    
    protected final String bestellNr;

    public DecoderTypKey(final IHersteller hersteller, final String bestellNr) {
        this.hersteller = hersteller;
        this.bestellNr = bestellNr;
    }

    public IHersteller getHerster() {
        return hersteller;
    }

    public String getBestellNr() {
        return bestellNr;
    }
    
    @Override
    public void addCriteria(Query query) {
        query.setParameter(DBNames.HERSTELLER, getHerster());
        query.setParameter(DBNames.NAME, getBestellNr());
    }
}
