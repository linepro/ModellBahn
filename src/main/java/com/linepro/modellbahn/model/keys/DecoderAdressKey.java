package com.linepro.modellbahn.model.keys;

import javax.persistence.Query;

import com.linepro.modellbahn.model.IDecoder;
import com.linepro.modellbahn.persistence.DBNames;
import com.linepro.modellbahn.rest.util.ApiNames;
import com.linepro.modellbahn.util.ToStringBuilder;

public class DecoderAdressKey extends BaseKey {

    private final IDecoder decoder;
    
    private final Integer reihe;

    public DecoderAdressKey(final IDecoder decoder, final Integer reihe) {
        this.decoder = decoder;
        this.reihe = reihe;
    }

    private IDecoder getDecoder() {
        return decoder;
    }

    private Integer getReihe() {
        return reihe;
    }

    @Override
    public void addCriteria(Query query) {
        query.setParameter(DBNames.DECODER, getDecoder());
        query.setParameter(DBNames.REIHE, getReihe());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append(ApiNames.DECODER, getDecoder())
                .append(ApiNames.REIHE, getReihe())
                .toString();
    }
}
