package com.linepro.modellbahn.model.keys;

import javax.persistence.Query;

import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.util.ToStringBuilder;

public class DecoderTypKey extends BaseKey {

    private final IHersteller hersteller;
    
    private final String bestellNr;

    public DecoderTypKey(final IHersteller hersteller, final String bestellNr) {
        this.hersteller = hersteller;
        this.bestellNr = bestellNr;
    }

    private IHersteller getHersteller() {
        return hersteller;
    }

    private String getBestellNr() {
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
