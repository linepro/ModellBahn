package com.linepro.modellbahn.model.keys;

import javax.persistence.Query;

import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.util.ToStringBuilder;

public class ProduktKey extends BaseKey {

    protected final IHersteller hersteller;
    
    protected final String bestellNr;

    public ProduktKey(final IHersteller hersteller, final String bestellNr) {
        this.hersteller = hersteller;
        this.bestellNr = bestellNr;
    }

    public IHersteller getHersteller() {
        return hersteller;
    }

    public String getBestellNr() {
        return bestellNr;
    }
    
    @Override
    public void addCriteria(Query query) {
        query.setParameter(DBNames.HERSTELLER, getHersteller());
        query.setParameter(DBNames.NAME, getBestellNr());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(ApiNames.HERSTELLER, getHersteller())
                .append(ApiNames.NAMEN, getBestellNr())
                .toString();
    }
}
