package com.linepro.modellbahn.model.keys;

import javax.persistence.Query;

import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.util.ToStringBuilder;

public class DecoderFunktionKey extends BaseKey {

    protected final IDecoder decoder;
    
    protected final String funktion;

    public DecoderFunktionKey(final IDecoder decoder, final String funktion) {
        this.decoder = decoder;
        this.funktion = funktion;
    }

    public IDecoder getDecoder() {
        return decoder;
    }

    public String getFunktion() {
        return funktion;
    }

    @Override
    public void addCriteria(Query query) {
        query.setParameter(DBNames.DECODER, getDecoder());
        query.setParameter(DBNames.FUNKTION, getFunktion());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(ApiNames.DECODER, getDecoder().getName())
                .append(ApiNames.FUNKTION, getFunktion())
                .toString();
    }
}
