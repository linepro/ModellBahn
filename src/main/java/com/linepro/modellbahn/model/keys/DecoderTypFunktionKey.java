package com.linepro.modellbahn.model.keys;

import javax.persistence.Query;

import com.linepro.modellbahn.model.IHersteller;
import com.linepro.modellbahn.persistence.DBNames;

public class DecoderTypFunktionKey extends ProduktKey {

    protected final Integer reihe;

    protected final String funktion;
    
    public DecoderTypFunktionKey(final IHersteller hersteller, final String bestellNr, final Integer reihe, final String funktion) {
        super(hersteller, bestellNr);
        this.reihe = reihe;
        this.funktion = funktion;
    }

    public Integer getReihe() {
        return reihe;
    }

    public String getFunktion() {
        return funktion;
    }

    @Override
    public void addCriteria(Query query) {
        super.addCriteria(query);
        query.setParameter(DBNames.REIHE, getReihe());
        query.setParameter(DBNames.FUNKTION, getFunktion());
    }
}
