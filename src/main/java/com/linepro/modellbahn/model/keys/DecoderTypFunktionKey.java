package com.linepro.modellbahn.model.keys;

import javax.persistence.Query;

import com.linepro.modellbahn.model.IDecoderTyp;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.util.ToStringBuilder;

public class DecoderTypFunktionKey extends BaseKey {

    protected final IDecoderTyp decoderTyp;

    protected final Integer reihe;

    protected final String funktion;
    
    public DecoderTypFunktionKey(final IDecoderTyp decoderTyp, final Integer reihe, final String funktion) {
        this.decoderTyp = decoderTyp;
        this.reihe = reihe;
        this.funktion = funktion;
    }

    public IDecoderTyp getDecoderTyp() {
        return decoderTyp;
    }

    public Integer getReihe() {
        return reihe;
    }

    public String getFunktion() {
        return funktion;
    }

    @Override
    public void addCriteria(Query query) {
        query.setParameter(DBNames.DECODER_TYP, getDecoderTyp());
        query.setParameter(DBNames.REIHE, getReihe());
        query.setParameter(DBNames.NAME, getFunktion());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(ApiNames.DECODER_TYP, getDecoderTyp())
                .append(ApiNames.REIHE, getReihe())
                .toString();
    }
}
