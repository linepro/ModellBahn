package com.linepro.modellbahn.model.keys;

import javax.persistence.Query;

import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.persistence.DBNames;

public class DecoderTypCVKey extends ProduktKey {

    protected final Integer cv;

    public DecoderTypCVKey(final IHersteller hersteller, final String bestellNr, final Integer cv) {
        super(hersteller, bestellNr);
        this.cv = cv;
    }

    public Integer getCv() {
        return cv;
    }

    @Override
    public void addCriteria(Query query) {
        super.addCriteria(query);
        query.setParameter(DBNames.CV, getCv());
    }
}
